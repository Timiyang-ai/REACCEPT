	@Test
	public void getCohorts_shouldMatchCohortsByPartialName() {
		executeDataSet(COHORT_XML);
		List<Cohort> matchedCohorts = service.getCohorts("Example");
		assertEquals(2, matchedCohorts.size());
		matchedCohorts = service.getCohorts("e Coh");
		assertEquals(2, matchedCohorts.size());
		matchedCohorts = service.getCohorts("hort");
		assertEquals(2, matchedCohorts.size());
		matchedCohorts = service.getCohorts("Examples");
		assertEquals(0, matchedCohorts.size());
	}