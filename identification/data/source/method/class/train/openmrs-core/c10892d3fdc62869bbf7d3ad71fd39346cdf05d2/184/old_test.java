	@Test
	public void purgePatientIdentifierType_shouldDeleteTypeFromDatabase() throws Exception {
		PatientIdentifierType patientIdentifierType = new PatientIdentifierType();
		
		patientIdentifierType.setName("testing");
		patientIdentifierType.setDescription("desc");
		patientIdentifierType.setRequired(false);
		
		patientService.savePatientIdentifierType(patientIdentifierType);
		
		PatientIdentifierType type = patientService.getPatientIdentifierType(patientIdentifierType.getId());
		
		patientService.purgePatientIdentifierType(type);
		assertNull(patientService.getPatientIdentifierType(patientIdentifierType.getId()));
	}