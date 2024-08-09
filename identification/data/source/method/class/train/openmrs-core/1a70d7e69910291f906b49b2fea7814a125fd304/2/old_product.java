public boolean hasRole(String r, boolean ignoreSuperUser) {
		if (ignoreSuperUser == false) {
			if (isSuperUser())
				return true;
		}
		
		if (roles == null)
			return false;
		
		Set<Role> tmproles = getAllRoles();
		
		log.debug("User #" + userId + " has roles: " + tmproles);
		
		Role role = new Role(r);
		
		if (tmproles.contains(role))
			return true;
		
		return false;
	}