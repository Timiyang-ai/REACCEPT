	@Test
	public void voidRelationship_shouldVoidRelationshipIfGivenRelationshipIsNotVoided() {
		
		Relationship relationship = personService.getRelationship(1);
		assertFalse("We need an unvoided relationship to test the method", relationship.getVoided());
		String voidReason = "Something";

		// TODO - voiding is done by the BaseVoidHandler called via AOP before voidRelationship
		// is executed. Coverage of voidRelationship is low because relationship.getVoided() is true
		// when entering voidRelationship
		// Documented at TRUNK-5151
		personService.voidRelationship(relationship, voidReason);

		Relationship voidedRelationship = personService.getRelationship(1);
		assertTrue(voidedRelationship.getVoided());
		assertThat(voidedRelationship.getVoidReason(), is(voidReason));
		assertNotNull(voidedRelationship.getDateVoided());
		assertEquals(voidedRelationship.getVoidedBy(), Context.getAuthenticatedUser());
	}