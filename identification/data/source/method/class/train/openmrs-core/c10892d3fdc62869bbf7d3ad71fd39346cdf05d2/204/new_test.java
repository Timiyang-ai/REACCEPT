	@Test
	public void getAllPatientIdentifierTypes_shouldFetchAllNonRetiredPatientIdentifierTypes() throws Exception {
		Collection<PatientIdentifierType> types = Context.getPatientService().getAllPatientIdentifierTypes();
		Assert.assertNotNull("Should not return null", types);
		
		for (PatientIdentifierType type : types) {
			if (type.getRetired()) {
				Assert.fail("Should not return retired patient identifier types");
			}
		}
		Assert.assertEquals("Should be exactly three patient identifier types in the dataset", 3, types.size());
		
	}