public boolean containsRole(String roleName) {
		for (Role role : getAllRoles()) {
			if (role.getRole().equalsIgnoreCase(roleName)) {
				return true;
			}
		}
		return false;
	}