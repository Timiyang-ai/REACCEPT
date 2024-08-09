	@Test
	public void saveObs_shouldRetrieveCorrectMimetype() throws IOException {
		String mimetype = "audio/mpeg";
		String filename = "TestingComplexObsSaving.mp3";
		File sourceFile = new File(
	        "src" + File.separator + "test" + File.separator + "resources" + File.separator + "ComplexObsTestAudio.mp3");
		
		FileInputStream in1 = new FileInputStream(sourceFile);
		FileInputStream in2 = new FileInputStream(sourceFile);
		
		ComplexData complexData1 = new ComplexData(filename, in1);
		ComplexData complexData2 = new ComplexData(filename, in2);
		
		// Construct 2 Obs to also cover the case where the filename exists already
		Obs obs1 = new Obs();
		obs1.setComplexData(complexData1);
		
		Obs obs2 = new Obs();
		obs2.setComplexData(complexData2);
		
		// Mocked methods
		mockStatic(Context.class);
		when(Context.getAdministrationService()).thenReturn(administrationService);
		when(administrationService.getGlobalProperty(any())).thenReturn(complexObsTestFolder.newFolder().getAbsolutePath());
		
		MediaHandler handler = new MediaHandler();
		
		// Execute save
		handler.saveObs(obs1);
		handler.saveObs(obs2);
		
		// Get observation
		Obs complexObs1 = handler.getObs(obs1, "RAW_VIEW");
		Obs complexObs2 = handler.getObs(obs2, "RAW_VIEW");
		
		assertEquals(complexObs1.getComplexData().getMimeType(), mimetype);
		assertEquals(complexObs2.getComplexData().getMimeType(), mimetype);
	}