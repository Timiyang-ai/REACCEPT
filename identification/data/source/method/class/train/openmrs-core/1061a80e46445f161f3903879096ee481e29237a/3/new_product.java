public boolean isSuperUser() {
		Set<Role> tmproles = getAllRoles();
		
		Role role = new Role(OpenmrsConstants.SUPERUSER_ROLE);	//default administrator with complete control
		
		if (tmproles.contains(role))
			return true;
		
		return false;
	}