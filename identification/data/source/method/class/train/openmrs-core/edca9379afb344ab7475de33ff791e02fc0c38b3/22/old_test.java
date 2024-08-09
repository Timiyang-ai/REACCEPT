	@Test
	public void getObservationCount_shouldGetCountOfObsAssignedToGivenEncounters() {
		ObsService obsService = Context.getObsService();
		
		Integer count = obsService.getObservationCount(null, Collections.singletonList(new Encounter(4)), null, null, null,
		    null, null, null, null, false, null);
		
		Assert.assertEquals(6, count.intValue());
	}