	@Test
	public void getPersonAttributeTypes_shouldReturnPersonAttributeTypesMatchingGivenParameters() throws Exception {
		executeDataSet("org/openmrs/api/include/PersonServiceTest-createRetiredPersonAttributeType.xml");
		
		List<PersonAttributeType> attributeTypes = Context.getPersonService().getPersonAttributeTypes(
		    "A nonexistent attr type name", null, null, null);
		Assert.assertNotNull(attributeTypes);
		assertTrue("Number of matched attribute type is 0", attributeTypes.isEmpty());
		
		attributeTypes = Context.getPersonService().getPersonAttributeTypes(null, "org.openmrs.Concept", null, null);
		Assert.assertNotNull(attributeTypes);
		assertTrue("Number of matched attribute type is 1", attributeTypes.size() == 1);
		
		attributeTypes = Context.getPersonService().getPersonAttributeTypes(null, null, null, false);
		Assert.assertNotNull(attributeTypes);
		assertTrue("Number of matched attribute type is 6", attributeTypes.size() == 6);
	}