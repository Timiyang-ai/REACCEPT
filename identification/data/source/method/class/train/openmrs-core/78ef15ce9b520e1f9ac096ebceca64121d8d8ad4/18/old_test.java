	@Test
	public void getObs_shouldGetObsMatchingGivenObsId() {
		ObsService obsService = Context.getObsService();
		
		Obs obs = obsService.getObs(7);
		
		Assert.assertEquals(5089, obs.getConcept().getId().intValue());
	}