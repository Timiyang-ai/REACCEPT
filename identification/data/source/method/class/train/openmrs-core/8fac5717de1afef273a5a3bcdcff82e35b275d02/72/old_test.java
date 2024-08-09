	@Test
	public void unvoidRelationship_shouldUnvoidVoidedRelationship() throws Exception {
		Relationship relationship = Context.getPersonService().getRelationship(1);
		Relationship voidedRelationship = Context.getPersonService().voidRelationship(relationship,
		    "Test Voiding Relationship");
		
		assertTrue(voidedRelationship.getVoided());
		Assert.assertNotNull(voidedRelationship.getVoidedBy());
		Assert.assertNotNull(voidedRelationship.getVoidReason());
		Assert.assertNotNull(voidedRelationship.getDateVoided());
		
		Relationship unvoidedRelationship = Context.getPersonService().unvoidRelationship(voidedRelationship);
		
		Assert.assertFalse(unvoidedRelationship.getVoided());
		Assert.assertNull(unvoidedRelationship.getVoidedBy());
		Assert.assertNull(unvoidedRelationship.getVoidReason());
		Assert.assertNull(unvoidedRelationship.getDateVoided());
	}