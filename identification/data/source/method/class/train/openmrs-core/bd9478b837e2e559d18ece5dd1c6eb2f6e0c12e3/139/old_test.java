	@Test
	public void getPatientIdentifierTypes_shouldFetchPatientIdentifierTypesThatMatchGivenNameWithGivenFormat()
	    throws Exception {
		executeDataSet("org/openmrs/api/include/PatientServiceTest-createPatientIdentifierType.xml");
		List<PatientIdentifierType> patientIdentifierTypes = Context.getPatientService().getPatientIdentifierTypes(
		    "Test OpenMRS Identification Number", "java.lang.Integer", null, null);
		
		Assert.assertEquals(false, patientIdentifierTypes.isEmpty());
		
		for (PatientIdentifierType patientIdentifierType : patientIdentifierTypes) {
			Assert.assertEquals("Test OpenMRS Identification Number", patientIdentifierType.getName());
			Assert.assertEquals("java.lang.Integer", patientIdentifierType.getFormat());
		}
	}