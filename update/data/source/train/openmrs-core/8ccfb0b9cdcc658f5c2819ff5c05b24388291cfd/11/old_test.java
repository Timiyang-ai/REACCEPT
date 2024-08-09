@Test
	@Verifies(value = "should find object given valid uuid", method = "getCohortByUuid(String)")
	public void getCohortByUuid_shouldFindObjectGivenValidUuid() throws Exception {
		executeDataSet(COHORT_XML);
		String uuid = "h9a9m0i6-15e6-467c-9d4b-mbi7teu9lf0f";
		Cohort cohort = Context.getCohortService().getCohortByUuid(uuid);
		Assert.assertEquals(1, (int) cohort.getCohortId());
	}