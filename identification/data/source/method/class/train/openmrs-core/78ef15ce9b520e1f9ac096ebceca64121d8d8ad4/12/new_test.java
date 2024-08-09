	@Test(expected = IllegalArgumentException.class)
	public void voidObs_shouldFailIfReasonParameterIsEmpty() {
		ObsService obsService = Context.getObsService();
		
		Obs obs = obsService.getObs(7);
		
		obsService.voidObs(obs, "");
	}