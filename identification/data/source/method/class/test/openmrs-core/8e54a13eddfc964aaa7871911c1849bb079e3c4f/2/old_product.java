@SuppressWarnings( { "unchecked" })
	public void before(Method method, Object[] args, Object target) throws Throwable {
		
		if (log.isDebugEnabled())
			log.debug("Calling authorization advice before " + method.getName());
		
		User user = Context.getAuthenticatedUser();
		
		if (log.isDebugEnabled()) {
			log.debug("User " + user);
			if (user != null)
				log.debug("has roles " + user.getAllRoles());
		}
		
		AuthorizedAnnotationAttributes attributes = new AuthorizedAnnotationAttributes();
		Collection<String> attrs = attributes.getAttributes(method);
		boolean requireAll = attributes.getRequireAll(method);
		
		// Only execute if the "secure" method has authorization attributes
		// Iterate through required privileges and return only if the user has
		// one of them
		if (attrs.size() > 0) {
			for (String privilege : attrs) {
				
				// skip null privileges
				if (privilege == null || privilege.length() < 1)
					return;
				
				if (log.isDebugEnabled())
					log.debug("User has privilege " + privilege + "? " + Context.hasPrivilege(privilege));
				
				if (Context.hasPrivilege(privilege)) {
					if (requireAll == false) {
						// if not all required, the first one that they have
						// causes them to "pass"
						return;
					}
				} else if (requireAll == true) {
					// if all are required, the first miss causes them
					// to "fail"
					throwUnauthorized(user, method, privilege);
				}
			}
			
			if (requireAll == false) {
				// If there's no match, then we know there are privileges and
				// that the user didn't have any of them. The user is not
				// authorized to access the method
				throwUnauthorized(user, method, attrs);
			}
			
		} else if (attributes.hasAuthorizedAnnotation(method)) {
			// if there are no privileges defined, just require that 
			// the user be authenticated
			if (Context.isAuthenticated() == false)
				throwUnauthorized(user, method);
		}
	}