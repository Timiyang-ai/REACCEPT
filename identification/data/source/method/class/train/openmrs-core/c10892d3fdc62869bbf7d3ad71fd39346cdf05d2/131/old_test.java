	@Test
	public void getAllPersonAttributeTypes_shouldReturnAllPersonAttributeTypesIncludingRetired() throws Exception {
		executeDataSet("org/openmrs/api/include/PersonServiceTest-createRetiredPersonAttributeType.xml");
		
		List<PersonAttributeType> attributeTypes = Context.getPersonService().getAllPersonAttributeTypes();
		assertTrue("At least one element, otherwise no checking for retired will take place",
		    attributeTypes.size() > 0);
		
		boolean foundRetired = false;
		for (PersonAttributeType personAttributeType : attributeTypes) {
			if (personAttributeType.getRetired()) {
				foundRetired = true;
				break;
			}
		}
		
		assertTrue("There should be at least one retired person attribute type found in the list", foundRetired);
	}