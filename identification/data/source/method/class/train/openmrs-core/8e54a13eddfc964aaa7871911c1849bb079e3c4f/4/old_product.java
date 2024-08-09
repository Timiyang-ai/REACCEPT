@SuppressWarnings( { "unchecked" })
	public void before(Method method, Object[] args, Object target) throws Throwable {
		
		if (log.isDebugEnabled()) {
			log.debug("Calling authorization advice before " + method.getName());
		}
		
		if (log.isDebugEnabled()) {
			User user = Context.getAuthenticatedUser();
			log.debug("User " + user);
			if (user != null) {
				log.debug("has roles " + user.getAllRoles());
			}
		}
		
		AuthorizedAnnotationAttributes attributes = new AuthorizedAnnotationAttributes();
		Collection<String> privileges = attributes.getAttributes(method);
		boolean requireAll = attributes.getRequireAll(method);
		
		// Only execute if the "secure" method has authorization attributes
		// Iterate through required privileges and return only if the user has
		// one of them
		if (!privileges.isEmpty()) {
			for (String privilege : privileges) {
				
				// skip null privileges
				if (privilege == null || privilege.isEmpty()) {
					return;
				}
				
				if (log.isDebugEnabled()) {
					log.debug("User has privilege " + privilege + "? " + Context.hasPrivilege(privilege));
				}
				
				if (Context.hasPrivilege(privilege)) {
					if (!requireAll) {
						// if not all required, the first one that they have
						// causes them to "pass"
						return;
					}
				} else {
					if (requireAll) {
						// if all are required, the first miss causes them
						// to "fail"
						throwUnauthorized(Context.getAuthenticatedUser(), method, privilege);
					}
				}
			}
			
			if (requireAll == false) {
				// If there's no match, then we know there are privileges and
				// that the user didn't have any of them. The user is not
				// authorized to access the method
				throwUnauthorized(Context.getAuthenticatedUser(), method, privileges);
			}
		} else if (attributes.hasAuthorizedAnnotation(method) && !Context.isAuthenticated()) {
			throwUnauthorized(Context.getAuthenticatedUser(), method);
		}
	}