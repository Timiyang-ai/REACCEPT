	@Test
	public void toString_shouldReturnToStringOfHydratedValue() {
		// type = CIVIL STATUS, concept = MARRIED
		PersonAttributeType type = Context.getPersonService().getPersonAttributeType(8);
		PersonAttribute attr = new PersonAttribute(type, "6");
		Assert.assertEquals("MARRIED", attr.toString());
	}