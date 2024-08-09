	@Test
	public void getObservationsByPerson_shouldGetAllObservationsAssignedToGivenPerson() {
		ObsService obsService = Context.getObsService();
		
		List<Obs> obss = obsService.getObservationsByPerson(new Person(7));
		
		Assert.assertEquals(9, obss.size());
		Assert.assertEquals(16, obss.get(0).getObsId().intValue());
		Assert.assertEquals(7, obss.get(8).getObsId().intValue());
	}