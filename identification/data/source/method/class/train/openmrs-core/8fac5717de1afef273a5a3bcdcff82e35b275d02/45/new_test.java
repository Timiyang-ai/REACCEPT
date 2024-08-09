	@Test
	public void voidCohort_shouldFailIfReasonIsEmpty() {
		executeDataSet(COHORT_XML);
		
		// Get a non-voided, valid Cohort and try to void it with a null reason
		Cohort exampleCohort = service.getCohortByName("Example Cohort");
		assertNotNull(exampleCohort);
		assertFalse(exampleCohort.getVoided());
		
		// Now get the Cohort and try to void it with an empty reason
		exampleCohort = service.getCohortByName("Example Cohort");
		assertNotNull(exampleCohort);
		assertFalse(exampleCohort.getVoided());
		
		try {
			service.voidCohort(exampleCohort, "");
			Assert.fail("voidCohort should fail with exception if reason is empty");
		}
		catch (Exception e) {}
	}