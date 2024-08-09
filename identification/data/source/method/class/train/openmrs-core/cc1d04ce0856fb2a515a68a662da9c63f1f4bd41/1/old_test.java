@Test(expected = APIException.class)
	@Verifies(value = "should not merge patient with itself", method = "mergePatients(Patient,Patient)")
	public void mergePatients_shouldNotMergePatientWithItself() {
		Context.getPatientService().mergePatients(new Patient(2), new Patient(2));
	}