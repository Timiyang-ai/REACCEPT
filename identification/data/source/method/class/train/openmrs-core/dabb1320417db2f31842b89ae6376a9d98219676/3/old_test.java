	@Test
	public void saveObs_shouldRetrieveCorrectMimetype() throws IOException {
		String mimetype = "application/octet-stream";
		String filename = "TestingComplexObsSaving";
		byte[] content = "Teststring".getBytes();
		
		ComplexData complexData = new ComplexData(filename, content);
		
		// Construct 2 Obs to also cover the case where the filename exists already
		Obs obs1 = new Obs();
		obs1.setComplexData(complexData);
		
		Obs obs2 = new Obs();
		obs2.setComplexData(complexData);
		
		// Mocked methods
		mockStatic(Context.class);
		when(Context.getAdministrationService()).thenReturn(administrationService);
		when(administrationService.getGlobalProperty(any())).thenReturn(complexObsTestFolder.newFolder().getAbsolutePath());
		
		BinaryDataHandler handler = new BinaryDataHandler();
		
		// Execute save
		handler.saveObs(obs1);
		handler.saveObs(obs2);
		
		// Get observation
		Obs complexObs1 = handler.getObs(obs1, "RAW_VIEW");
		Obs complexObs2 = handler.getObs(obs2, "RAW_VIEW");
		
		assertEquals(complexObs1.getComplexData().getMimeType(), mimetype);
		assertEquals(complexObs2.getComplexData().getMimeType(), mimetype);
	}