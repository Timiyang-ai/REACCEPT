@Test
	@Verifies(value = "should update an existing cohort", method = "saveCohort(Cohort)")
	public void saveCohort_shouldUpdateAnExistingCohort() throws Exception {
		executeDataSet(COHORT_XML);
		
		// get and modify a cohort in the  data set
		String modifiedCohortDescription = "This description has been modified in a test";
		Cohort cohortToModify = service.getCohort(2);
		cohortToModify.setDescription(modifiedCohortDescription);
		
		// save the modified cohort back to the data set, see if the modification is there
		service.saveCohort(cohortToModify);
		assertTrue(service.getCohort(2).getDescription().equals(modifiedCohortDescription));
	}