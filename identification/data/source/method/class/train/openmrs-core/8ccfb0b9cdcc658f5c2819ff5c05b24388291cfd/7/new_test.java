@Test
	public void voidCohort_shouldFailIfReasonIsNull() throws Exception {
		executeDataSet(COHORT_XML);
		
		// Get a non-voided, valid Cohort and try to void it with a null reason
		Cohort exampleCohort = service.getCohort("Example Cohort");
		assertNotNull(exampleCohort);
		assertFalse(exampleCohort.getVoided());
		
		try {
			service.voidCohort(exampleCohort, null);
			Assert.fail("voidCohort should fail with exception if reason is null.");
		}
		catch (Exception e) {}
		
		// Now get the Cohort and try to void it with an empty reason
		exampleCohort = service.getCohort("Example Cohort");
		assertNotNull(exampleCohort);
		assertFalse(exampleCohort.getVoided());
		
		try {
			service.voidCohort(exampleCohort, "");
			Assert.fail("voidCohort should fail with exception if reason is empty");
		}
		catch (Exception e) {}
	}