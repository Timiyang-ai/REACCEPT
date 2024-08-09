@RequestMapping(value="/admin/users/user.form", method=RequestMethod.POST)
	public String handleSubmission(WebRequest request,
	                               HttpSession httpSession,
	                               ModelMap model,
	                               @RequestParam(required=false, value="action") String action,
	                               @RequestParam(required=false, value="userFormPassword") String password,
	                               @RequestParam(required=false, value="confirm") String confirm,
	                               @RequestParam(required=false, value="forcePassword") Boolean forcePassword,
	                               @RequestParam(required=false, value="roleStrings") String[] roles,
	                               @RequestParam(required=false, value="createNewPerson") String createNewPerson,
	                               @ModelAttribute("user") User user, BindingResult errors) {
		
		UserService us = Context.getUserService();
		MessageSourceService mss = Context.getMessageSourceService();
		
		if (!Context.isAuthenticated()) {
			errors.reject("auth.invalid");
		} else if (mss.getMessage("User.assumeIdentity").equals(action)) {
			Context.becomeUser(user.getSystemId());
			httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "User.assumeIdentity.success");
			httpSession.setAttribute(WebConstants.OPENMRS_MSG_ARGS, user.getPersonName());
			return "redirect:/index.htm";
				
		} else if (mss.getMessage("User.delete").equals(action)) {
			try {
				Context.getUserService().purgeUser(user);
				httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "User.delete.success");
			} catch (Exception ex) {
				httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "User.delete.failure");
				httpSession.setAttribute(WebConstants.OPENMRS_MSG_ARGS, ex.getMessage());
				log.error("Failed to delete user", ex);
			}
			return "redirect:/index.htm";
		
		} else {
				
			// check if username is already in the database
			if (us.hasDuplicateUsername(user))
				errors.rejectValue("username", "error.username.taken");
			
			// check if password and password confirm are identical
			if (password == null || password.equals("XXXXXXXXXXXXXXX"))
				password = "";
			if (confirm == null || confirm.equals("XXXXXXXXXXXXXXX"))
				confirm = "";
			
			if (!password.equals(confirm))
				errors.reject("error.password.match");
			
			if (password.length() == 0 && isNewUser(user))
				errors.reject("error.password.weak");
			
			//check password strength
			if (password.length() > 0) {
				try {
					OpenmrsUtil.validatePassword(user.getUsername(), password, user.getSystemId());
				}
				catch (PasswordException e) {
					errors.reject(e.getMessage());
				}
			}
			
			Set<Role> newRoles = new HashSet<Role>();
			if (roles != null) {
				for (String r : roles) {
					// Make sure that if we already have a detached instance of this role in the
					// user's roles, that we don't fetch a second copy of that same role from
					// the database, or else hibernate will throw a NonUniqueObjectException.
					Role role = null;
					if (user.getRoles() != null)
						for (Role test : user.getRoles())
							if (test.getRole().equals(r))
								role = test;
					if (role == null) {
						role = us.getRole(r);
						user.addRole(role);
					}
					newRoles.add(role);
				}
			}
			
			if (user.getRoles() == null)
				newRoles.clear();
			else
				user.getRoles().retainAll(newRoles);
			

			String[] keys = request.getParameterValues("property");
			String[] values = request.getParameterValues("value");
			
			if (keys != null && values != null) {
				for (int x = 0; x < keys.length; x++) {
					String key = keys[x];
					String val = values[x];
					user.setUserProperty(key, val);
				}
			}
							
			new UserProperties(user.getUserProperties()).setSupposedToChangePassword(forcePassword);
			
			UserValidator uv = new UserValidator();
			uv.validate(user, errors);
			
			if (errors.hasErrors()) {
				return showForm(user.getUserId(), createNewPerson, user, model);
			}
			
			if (isNewUser(user))
				us.saveUser(user, password);
			else {
				us.saveUser(user, null);
				
				if (!password.equals("") && Context.hasPrivilege(OpenmrsConstants.PRIV_EDIT_USER_PASSWORDS)) {
					if (log.isDebugEnabled())
						log.debug("calling changePassword for user " + user + " by user " + Context.getAuthenticatedUser());
					us.changePassword(user, password);
				}
				
			}

			httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "User.saved");
		}
		return "redirect:user.list";
	}