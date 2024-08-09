	@Test(expected = PatientIdentifierTypeLockedException.class)
	public void retirePatientIdentifierType_shouldThrowErrorWhenTryingToRetireAPatientIdentifierTypeWhilePatientIdentifierTypesAreLocked()
	    throws Exception {
		PatientService ps = Context.getPatientService();
		createPatientIdentifierTypeLockedGPAndSetValue("true");
		PatientIdentifierType pit = ps.getPatientIdentifierType(1);
		ps.retirePatientIdentifierType(pit, "Retire test");
	}