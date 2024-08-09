@Test
	public void testGetDialogs(){
		List<Dialog> dialogs = service.getDialogs();
		assertNotNull(dialogs);
		assertFalse(dialogs.isEmpty());
	}