	@Test
	public void getPatientByExample_shouldFetchPatientMatchingPatientIdOfGivenPatient() throws Exception {
		Patient examplePatient = Context.getPatientService().getPatient(6);
		examplePatient.setId(2);
		
		Patient patient = Context.getPatientService().getPatientByExample(examplePatient);
		Assert.assertNotNull(patient);
		Assert.assertTrue(patient.getClass().isAssignableFrom(Patient.class));
		Assert.assertEquals(Integer.valueOf(2), patient.getPatientId());
	}