@Test
	public void testCreateDialog() throws URISyntaxException {
		File dialogFile = new File("src/test/resources/pizza_sample.xml");
		String dialogName = ""+UUID.randomUUID().toString().substring(0, 15);
		Dialog newDialog = service.createDialog(dialogName, dialogFile);
		
		assertNotNull(newDialog.getId());
		newDialog = service.updateDialog(newDialog.getId(), dialogFile);
		assertNotNull(newDialog.getId());
		service.deleteDialog(newDialog.getId());
	}