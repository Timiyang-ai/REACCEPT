public boolean hasPrivilege(String privilegeName) {
		
		if (RoleConstants.SUPERUSER.equals(this.role))
			return true;
		
		if (privileges != null) {
			for (Privilege p : privileges) {
				if (p.getPrivilege().equals(privilegeName))
					return true;
			}
		}
		
		return false;
	}