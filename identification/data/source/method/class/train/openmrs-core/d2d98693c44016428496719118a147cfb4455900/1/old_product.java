private boolean containsRole(String roleName) {
		for (Role role : getAllRoles()) {
			if (role.getRole().equals(roleName)) {
				return true;
			}
		}
		return false;
	}