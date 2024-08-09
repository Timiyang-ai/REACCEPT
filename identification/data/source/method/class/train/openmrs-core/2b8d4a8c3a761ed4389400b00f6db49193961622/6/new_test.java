	@Test
	public void getCountOfPatients_shouldObeyAttributeMatchMode() {
		// exact match mode
		long patientCount = dao.getCountOfPatients("Cook");
		Assert.assertEquals(1, patientCount);
		
		patientCount = dao.getCountOfPatients("ook");
		Assert.assertEquals(0, patientCount);
		
		globalPropertiesTestHelper.setGlobalProperty(OpenmrsConstants.GLOBAL_PROPERTY_PERSON_ATTRIBUTE_SEARCH_MATCH_MODE,
		    OpenmrsConstants.GLOBAL_PROPERTY_PERSON_ATTRIBUTE_SEARCH_MATCH_ANYWHERE);
		
		patientCount = dao.getCountOfPatients("ook");
		Assert.assertEquals(1, patientCount);
	}