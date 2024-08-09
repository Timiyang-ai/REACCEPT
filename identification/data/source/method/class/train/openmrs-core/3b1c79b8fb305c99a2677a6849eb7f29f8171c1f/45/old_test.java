	@Test(expected = APIException.class)
	public void getDuplicatePatientsByAttributes_shouldThrowErrorGivenEmptyAttributes() throws Exception {
		patientService.getDuplicatePatientsByAttributes(Collections.emptyList());
	}