public boolean isSuperUser() {
		Set<Role> tmproles = getRoles();
		
		if (tmproles == null)
			tmproles = new HashSet<Role>();

		if (groups != null)
			for (Group g : groups) {
				tmproles.addAll(g.getRoles());
			}
		
		Role role = new Role(OpenmrsConstants.SUPERUSER_ROLE);	//default administrator with complete control
		
		if (tmproles.contains(role))
			return true;
		
		return false;
	}