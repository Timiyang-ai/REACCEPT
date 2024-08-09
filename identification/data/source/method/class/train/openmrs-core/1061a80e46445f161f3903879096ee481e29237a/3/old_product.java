public boolean isSuperUser() {
		Set<Role> tmproles = new HashSet<Role>();
		tmproles.addAll(getRoles());
		
		if (groups != null)
			for (Group g : groups) {
				tmproles.addAll(g.getRoles());
			}
		
		Role role = new Role(OpenmrsConstants.SUPERUSER_ROLE);	//default administrator with complete control
		
		if (tmproles.contains(role))
			return true;
		
		return false;
	}