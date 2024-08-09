	@Test
	public void getPatientIdentifierTypes_shouldReturnNonRetiredPatientIdentifierTypesWithGivenName() {
		PatientIdentifierType oldIdNumberNonRetired = dao.getPatientIdentifierType(2);
		
		List<PatientIdentifierType> patientIdentifierTypes = dao.getPatientIdentifierTypes("Old Identification Number",
		    null, null, null);
		
		Assert.assertEquals(patientIdentifierTypes.size(), 1);
		Assert.assertEquals(oldIdNumberNonRetired, patientIdentifierTypes.get(0));
	}