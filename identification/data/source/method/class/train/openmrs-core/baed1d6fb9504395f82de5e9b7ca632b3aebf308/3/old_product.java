@RequestMapping(value = "/admin/users/user.form", method = RequestMethod.POST)
	public String handleSubmission(WebRequest request, HttpSession httpSession, ModelMap model,
	        @RequestParam(required = false, value = "action") String action,
	        @RequestParam(required = false, value = "userFormPassword") String password,
	        @RequestParam(required = false, value = "secretQuestion") String secretQuestion,
	        @RequestParam(required = false, value = "secretAnswer") String secretAnswer,
	        @RequestParam(required = false, value = "confirm") String confirm,
	        @RequestParam(required = false, value = "forcePassword") Boolean forcePassword,
	        @RequestParam(required = false, value = "roleStrings") String[] roles,
	        @RequestParam(required = false, value = "createNewPerson") String createNewPerson,
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
				return "redirect:users.list";
			}
			catch (Exception ex) {
				httpSession.setAttribute(WebConstants.OPENMRS_ERROR_ATTR, "User.delete.failure");
				log.error("Failed to delete user", ex);
				return "redirect:/admin/users/user.form?userId=" + request.getParameter("userId");
			}
			
		} else if (mss.getMessage("User.retire").equals(action)) {
			String retireReason = request.getParameter("retireReason");
			if (!(StringUtils.hasText(retireReason))) {
				errors.rejectValue("retireReason", "User.disableReason.empty");
				return showForm(user.getUserId(), createNewPerson, user, model);
			} else {
				us.retireUser(user, retireReason);
				httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "User.retiredMessage");
			}
			
		} else if (mss.getMessage("User.unRetire").equals(action)) {
			us.unretireUser(user);
			httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "User.unRetiredMessage");
		} else {
			
			// check if username is already in the database
			if (us.hasDuplicateUsername(user)) {
				errors.rejectValue("username", "error.username.taken");
			}
			
			// check if password and password confirm are identical
			if (password == null || password.equals("XXXXXXXXXXXXXXX")) {
				password = "";
			}
			if (confirm == null || confirm.equals("XXXXXXXXXXXXXXX")) {
				confirm = "";
			}
			
			if (!password.equals(confirm)) {
				errors.reject("error.password.match");
			}
			
			if (password.length() == 0 && isNewUser(user)) {
				errors.reject("options.login.password.null");
			}
			
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
					if (user.getRoles() != null) {
						for (Role test : user.getRoles()) {
							if (test.getRole().equals(r)) {
								role = test;
							}
						}
					}
					if (role == null) {
						role = us.getRole(r);
						user.addRole(role);
					}
					newRoles.add(role);
				}
			}
			
			if (user.getRoles() == null) {
				newRoles.clear();
			} else {
				user.getRoles().retainAll(newRoles);
			}
			
			String[] keys = request.getParameterValues("property");
			String[] values = request.getParameterValues("value");
			
			if (keys != null && values != null) {
				for (int x = 0; x < keys.length; x++) {
					String key = keys[x];
					String val = values[x];
					user.setUserProperty(key, val);
				}
			}
			
			if (StringUtils.hasLength(secretQuestion) && !StringUtils.hasLength(secretAnswer)) {
				errors.reject("error.User.secretAnswer.empty");
			} else if (!StringUtils.hasLength(secretQuestion) && StringUtils.hasLength(secretAnswer)) {
				errors.reject("error.User.secretQuestion.empty");
			}
			
			new UserProperties(user.getUserProperties()).setSupposedToChangePassword(forcePassword);
			
			UserValidator uv = new UserValidator();
			uv.validate(user, errors);
			
			if (errors.hasErrors()) {
				return showForm(user.getUserId(), createNewPerson, user, model);
			}
			
			if (isNewUser(user)) {
				us.saveUser(user, password);
			} else {
				us.saveUser(user, null);
				
				if (!"".equals(password) && Context.hasPrivilege(PrivilegeConstants.EDIT_USER_PASSWORDS)) {
					if (log.isDebugEnabled()) {
						log.debug("calling changePassword for user " + user + " by user " + Context.getAuthenticatedUser());
					}
					us.changePassword(user, password);
				}
			}
			
			if (StringUtils.hasLength(secretQuestion) && StringUtils.hasLength(secretAnswer)) {
				us.changeQuestionAnswer(user, secretQuestion, secretAnswer);
			}
			
			httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "User.saved");
		}
		return "redirect:users.list";
	}