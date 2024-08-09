	@Test
	@Ignore
	//TODO: This test fails for some reason
	public void getPresentationLocales_shouldReturnAtLeastOneLocaleIfNoLocalesDefinedInDatabaseYet() {
		assertTrue(adminService.getPresentationLocales().size() > 0);
	}