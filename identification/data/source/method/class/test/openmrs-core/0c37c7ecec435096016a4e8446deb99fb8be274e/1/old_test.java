	@Test
	public void saveCondition_shouldSaveNewCondition(){
		Integer patientId = 2;
		String uuid = "08002000-4469-12q3-551f-0339000c9a76";
		CodedOrFreeText codedOrFreeText = new CodedOrFreeText();
		Condition condition = new Condition();
		condition.setCondition(codedOrFreeText);
		condition.setClinicalStatus(ConditionClinicalStatus.ACTIVE);
		condition.setUuid(uuid);
		condition.setPatient(new Patient(patientId));
		conditionService.saveCondition(condition);
		Condition savedCondition = conditionService.getConditionByUuid(uuid);
		Assert.assertEquals(patientId, savedCondition.getPatient().getPatientId());
		Assert.assertEquals(uuid, savedCondition.getUuid());
		Assert.assertEquals(codedOrFreeText, savedCondition.getCondition());
		Assert.assertEquals(ConditionClinicalStatus.ACTIVE, savedCondition.getClinicalStatus());
		Assert.assertNotNull(savedCondition.getConditionId());
	}