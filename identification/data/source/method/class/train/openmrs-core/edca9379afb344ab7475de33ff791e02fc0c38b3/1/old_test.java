	@Test
	public void purgeObs_shouldDeleteTheGivenObsFromTheDatabase() {
		ObsService obsService = Context.getObsService();
		Obs obs = obsService.getObs(7);
		
		obsService.purgeObs(obs);
		
		Assert.assertNull(obsService.getObs(7));
		
		
		executeDataSet(COMPLEX_OBS_XML);
		Obs complexObs = obsService.getComplexObs(44, ComplexObsHandler.RAW_VIEW);
		// obs #44 is coded by the concept complex #8473 pointing to ImageHandler
		// ImageHandler inherits AbstractHandler which handles complex data files on disk
		assertNotNull(complexObs.getComplexData());
		AdministrationService as = Context.getAdministrationService();
		File complexObsDir = OpenmrsUtil.getDirectoryInApplicationDataDirectory(as
		        .getGlobalProperty(OpenmrsConstants.GLOBAL_PROPERTY_COMPLEX_OBS_DIR));
		for (File file : complexObsDir.listFiles()) {
			file.delete();
		}

		obsService.purgeObs(complexObs);
		
		assertNull(obsService.getObs(obs.getObsId()));
	}