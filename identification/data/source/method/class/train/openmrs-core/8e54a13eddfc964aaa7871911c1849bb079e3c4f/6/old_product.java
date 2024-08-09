@SuppressWarnings({"unchecked"})
	public void before(Method method, Object[] args, Object target) throws Throwable { 
		log.info("Calling authorization advice before " + method.getName());
		
		User user = Context.getAuthenticatedUser();
		log.info("User " + user);
		if (user != null) 
			log.info("has roles " + user.getAllRoles());

		Attributes attributes = new AuthorizedAnnotationAttributes();
		Collection<String> attrs = attributes.getAttributes(method);
		
		// Only execute if the "secure" method has authorization attributes
		// Iterate through required privileges and return only if the user has one of them
		if (attrs != null && attrs.size() > 0) {
			for (String privilege : attrs) {
				if (privilege == null || privilege.length() < 1) return;
				log.info("User has privilege " + privilege + "? " + Context.hasPrivilege(privilege));
				if ( Context.hasPrivilege(privilege) ) return;
			}
			// If there's no match, then the user is not authorized to access the method
			log.info("User " + user + " is not authorized to access " + method.getName());
			throw new APIAuthenticationException("Privilege required: " + attrs);
		}
	}