@Test
	@Verifies(value = "should ignore voided patients", method = "isIdentifierInUseByAnotherPatient(PatientIdentifier)")
	public void isIdentifierInUseByAnotherPatient_shouldIgnoreVoidedPatients() throws Exception {
		{
			// patient 999 should be voided and have a non-voided identifier of
			// XYZ
			Patient p = patientService.getPatient(999);
			Assert.assertNotNull(p);
			Assert.assertTrue(p.isVoided());
			boolean found = false;
			for (PatientIdentifier id : p.getIdentifiers()) {
				if (id.getIdentifier().equals("XYZ") && id.getIdentifierType().getId() == 2) {
					found = true;
					break;
				}
			}
			Assert.assertTrue(found);
		}
		PatientIdentifierType pit = patientService.getPatientIdentifierType(2);
		PatientIdentifier patientIdentifier = new PatientIdentifier("XYZ", pit, null);
		Assert.assertFalse(patientService.isIdentifierInUseByAnotherPatient(patientIdentifier));
	}