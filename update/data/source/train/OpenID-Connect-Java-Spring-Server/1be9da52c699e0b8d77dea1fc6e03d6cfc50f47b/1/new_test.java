@Test
	public void testCreate_ticket() {
		
		PermissionTicket perm = permissionService.createTicket(rs1, scopes1);
		
		// we want there to be a non-null ticket
		assertNotNull(perm.getTicket());
	}