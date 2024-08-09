	@SkipBaseSetup
	@Test
	public void purgePatient_shouldDeletePatientFromDatabase() throws Exception {
		initializeInMemoryDatabase();
		executeDataSet(FIND_PATIENTS_XML);
		authenticate();
		
		// verify patient with ID 2 exists in database
		Patient patientToPurge = patientService.getPatient(2);
		assertNotNull(patientToPurge);
		
		// purge the patient
		patientService.purgePatient(patientToPurge);
		// if the patient doesn't exist in the database, getPatient should
		// return null now
		assertNull(patientService.getPatient(2));
	}