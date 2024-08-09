	@Test
	public void getAllPatients_shouldFetchAllNonVoidedPatients() throws Exception {
		List<Patient> allPatients = patientService.getAllPatients();
		// there are 1 voided and 4 nonvoided patients in
		// standardTestDataset.xml
		assertEquals(4, allPatients.size());
	}