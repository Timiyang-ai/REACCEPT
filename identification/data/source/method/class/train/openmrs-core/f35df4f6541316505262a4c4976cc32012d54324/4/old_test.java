	@Test
	public void getCountOfPatients_shouldReturnTheRightCountWhenAPatientHasMultipleMatchingPersonNames() throws Exception {
		// TODO H2 cannot execute the generated SQL because it requires all
		// fetched columns to be included in the group by clause
		Patient patient = patientService.getPatient(2);
		// sanity check
		Assert.assertTrue(patient.getPersonName().getGivenName().startsWith("Horati"));
		// add a name that will match the search phrase
		patient.addName(new PersonName("Horatio", "Test", "name"));
		Context.getPatientService().savePatient(patient);
		Assert.assertEquals(1, Context.getPatientService().getCountOfPatients("Hor").intValue());
	}