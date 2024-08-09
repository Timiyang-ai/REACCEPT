public boolean isSuperUser() {
		Set<Role> roles = getRoles();

		if (groups != null)
			for (Group g : groups) {
				roles.addAll(g.getRoles());
			}
		
		Role role = new Role(OpenmrsConstants.SUPERUSER_ROLE);	//default administrator with complete control
		
		if (roles.contains(role))
			return true;
		
		return false;
	}