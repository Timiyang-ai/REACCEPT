public boolean hasRole(String r, boolean ignoreSuperUser) {
		if (ignoreSuperUser == false) {
			if (isSuperUser())
				return true;
		}
		
		if (roles == null)
			return false;
		
		Set<Role> tmproles = getAllRoles();
		
		if (log.isDebugEnabled())
			log.debug("User #" + userId + " has roles: " + tmproles);
		
		return containsRole(r);
	}