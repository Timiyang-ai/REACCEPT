	@Test
	public void unretirePatientIdentifierType_shouldUnretirePatientIdentifierType() throws Exception {
		PatientIdentifierType identifierType = Context.getPatientService().getPatientIdentifierType(4);
		Assert.assertTrue(identifierType.getRetired());
		Assert.assertNotNull(identifierType.getRetiredBy());
		Assert.assertNotNull(identifierType.getRetireReason());
		Assert.assertNotNull(identifierType.getDateRetired());
		
		PatientIdentifierType unretiredIdentifierType = Context.getPatientService().unretirePatientIdentifierType(
		    identifierType);
		Assert.assertFalse(unretiredIdentifierType.getRetired());
		Assert.assertNull(unretiredIdentifierType.getRetiredBy());
		Assert.assertNull(unretiredIdentifierType.getRetireReason());
		Assert.assertNull(unretiredIdentifierType.getDateRetired());
	}