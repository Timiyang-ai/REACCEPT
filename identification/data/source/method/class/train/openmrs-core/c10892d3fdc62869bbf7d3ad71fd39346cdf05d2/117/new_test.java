	@Test
	public void saveObs_shouldCreateNewFileFromComplexDataForNewObs() {
		executeDataSet(COMPLEX_OBS_XML);
		ObsService os = Context.getObsService();
		ConceptService cs = Context.getConceptService();
		AdministrationService as = Context.getAdministrationService();
				
		// the complex data to put onto an obs that will be saved
		Reader input = new CharArrayReader("This is a string to save to a file".toCharArray());
		ComplexData complexData = new ComplexData("nameOfFile.txt", input);
		
		// must fetch the concept instead of just new Concept(8473) because the attributes on concept are checked
		// this is a concept mapped to the text handler
		Concept questionConcept = cs.getConcept(8474);
		
		Obs obsToSave = new Obs(new Person(1), questionConcept, new Date(), new Location(1));
		obsToSave.setComplexData(complexData);
		
		// make sure the file isn't there to begin with
		String filename = "nameOfFile_" + obsToSave.getUuid() + ".txt";
		File complexObsDir = OpenmrsUtil.getDirectoryInApplicationDataDirectory(as
	        .getGlobalProperty(OpenmrsConstants.GLOBAL_PROPERTY_COMPLEX_OBS_DIR));
		File createdFile = new File(complexObsDir, filename);
		if (createdFile.exists()) {
			createdFile.delete();
		}
		
		try {
			os.saveObs(obsToSave, null);
			
			// make sure the file appears now after the save
			Assert.assertTrue(createdFile.exists());
		}
		finally {
			// we always have to delete this inside the same unit test because it is outside the
			// database and hence can't be "rolled back" like everything else
			createdFile.delete();
		}
	}