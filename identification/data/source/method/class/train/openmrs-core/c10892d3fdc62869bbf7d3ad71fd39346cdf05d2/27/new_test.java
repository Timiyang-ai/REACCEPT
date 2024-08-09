	@Test
	public void getAllRelationships_shouldReturnAllUnvoidedRelationships() throws Exception {
		executeDataSet("org/openmrs/api/include/PersonServiceTest-createRetiredRelationship.xml");
		
		List<Relationship> relationships = Context.getPersonService().getAllRelationships();
		assertTrue("At least one element, otherwise no checking for voided will take place",
		            relationships.size() > 0);
		
		boolean foundVoided = false;
		for (Relationship relationship : relationships) {
			if (relationship.getVoided()) {
				foundVoided = true;
				break;
			}
		}
		
		Assert.assertFalse("There should be no voided relationship here", foundVoided);
	}