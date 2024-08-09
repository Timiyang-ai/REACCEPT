	@Test
	public void unvoidObs_shouldCascadeUnvoidToChildGroupedObs() {
		executeDataSet(INITIAL_OBS_XML);
		
		ObsService obsService = Context.getObsService();
		
		// a obs with child groups
		Obs parentObs = obsService.getObs(2);
		
		obsService.voidObs(parentObs, "testing void cascade to child obs groups");
		
		Assert.assertTrue(obsService.getObs(9).getVoided());
		Assert.assertTrue(obsService.getObs(10).getVoided());
	}