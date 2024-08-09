	@Test
	public void savePrivilege_shouldSaveGivenPrivilegeToTheDatabase() {
		Privilege p = new Privilege("new privilege name", "new privilege desc");
		userService.savePrivilege(p);
		
		Privilege savedPrivilege = userService.getPrivilege("new privilege name");
		Assert.assertNotNull(savedPrivilege);
		
	}