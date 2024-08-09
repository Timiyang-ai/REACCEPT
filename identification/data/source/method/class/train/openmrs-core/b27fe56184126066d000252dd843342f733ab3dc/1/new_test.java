	@Test
	public void getUniqueDiagnoses_shouldGetUniqueDiagnosesOfPatient(){
		Patient patient = patientService.getPatient(2);
		List<Diagnosis> diagnoses = diagnosisService.getUniqueDiagnoses(patient, new Date(0));
		
		Assert.assertEquals("68802cce-6880-17e4-6880-a68804d22fb7", diagnoses.get(0).getUuid());
		Assert.assertEquals(ConditionVerificationStatus.CONFIRMED, diagnoses.get(0).getCertainty());
		Assert.assertEquals(new Integer(1), diagnoses.get(0).getDiagnosisId());
		Assert.assertEquals(new Integer(2), diagnoses.get(0).getPatient().getPatientId());
		Assert.assertEquals(1, diagnoses.size());
	}