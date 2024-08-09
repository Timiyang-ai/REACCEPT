	@Test
	public void getObservationsByPersonAndConcept_shouldGetObservationsMatchingPersonAndQuestion() {
		ObsService obsService = Context.getObsService();
		
		List<Obs> obss = obsService.getObservationsByPersonAndConcept(new Person(7), new Concept(5089));
		
		Assert.assertEquals(3, obss.size());
		Assert.assertEquals(16, obss.get(0).getObsId().intValue());
		Assert.assertEquals(10, obss.get(1).getObsId().intValue());
		Assert.assertEquals(7, obss.get(2).getObsId().intValue());
	}