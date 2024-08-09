@Test
	@Verifies(value = "should never return null", method = "getCohorts(String)")
	public void getCohorts_shouldNeverReturnNull() throws Exception {
		executeDataSet(COHORT_XML);
		
		String invalidFragment = "Not Present";
		//data set should have two cohorts, one of which is voided
		List<Cohort> allCohorts = service.getCohorts(invalidFragment);
		assertNotNull(allCohorts);
	}