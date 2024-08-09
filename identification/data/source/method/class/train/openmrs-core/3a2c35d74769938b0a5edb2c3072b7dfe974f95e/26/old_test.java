	@Test(expected = APIException.class)
	public void mergePatients_shouldNotMergePatientWithItself() throws Exception {
		Context.getPatientService().mergePatients(new Patient(2), new Patient(2));
	}