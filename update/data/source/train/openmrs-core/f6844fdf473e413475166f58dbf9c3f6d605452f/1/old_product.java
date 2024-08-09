public boolean hasPrivilege(String privilegeName) {
		
		if (OpenmrsConstants.SUPERUSER_ROLE.equals(this.role))
			return true;
		
		if (privileges != null) {
			for (Privilege p : privileges) {
				if (p.getPrivilege().equals(privilegeName))
					return true;
			}
		}
		
		return false;
	}