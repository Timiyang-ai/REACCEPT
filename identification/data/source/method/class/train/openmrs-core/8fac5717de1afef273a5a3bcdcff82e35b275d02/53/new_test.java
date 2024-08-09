	@Test
	public void getRelationship_shouldReturnRelationshipWithGivenId() throws Exception {
		Relationship relationship = Context.getPersonService().getRelationship(1);
		Assert.assertNotNull(relationship);
	}