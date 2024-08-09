	@Test
	public void getProgramByName_shouldReturnProgramWhenNameMatches() {
		Program p = pws.getProgramByName("program name");
		assertNotNull(p);
	}