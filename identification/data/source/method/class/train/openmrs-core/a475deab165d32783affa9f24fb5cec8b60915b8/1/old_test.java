@Test
	@Verifies(value = "should untire patient identifier type", method = "unretirePatientIdentifierType(PatientIdentifierType)")
	public void unretirePatientIdentifierType_shouldUntirePatientIdentifierType() throws Exception {
		PatientIdentifierType identifierType = Context.getPatientService().getPatientIdentifierType(4);
		Assert.assertTrue(identifierType.isRetired());
		Assert.assertNotNull(identifierType.getRetiredBy());
		Assert.assertNotNull(identifierType.getRetireReason());
		Assert.assertNotNull(identifierType.getDateRetired());
		
		PatientIdentifierType unretiredIdentifierType = Context.getPatientService().unretirePatientIdentifierType(
		    identifierType);
		Assert.assertFalse(unretiredIdentifierType.isRetired());
		Assert.assertNull(unretiredIdentifierType.getRetiredBy());
		Assert.assertNull(unretiredIdentifierType.getRetireReason());
		Assert.assertNull(unretiredIdentifierType.getDateRetired());
	}