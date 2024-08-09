	@Test
	public void getAllEncounters_shouldGetAllEncountersForACohortOfPatients() {
		Cohort cohort = new Cohort();
		cohort.addMember(7);
		Map<Integer, List<Encounter>> allEncounters = Context.getEncounterService().getAllEncounters(cohort);
		Assert.assertEquals(1, allEncounters.size());
		Assert.assertEquals(3, allEncounters.get(7).size());
	}