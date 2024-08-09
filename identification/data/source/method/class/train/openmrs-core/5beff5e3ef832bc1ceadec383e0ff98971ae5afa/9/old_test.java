	@Test
	public void validate_shouldFailIfEndDateIsBeforeStartDate() throws ParseException {
		Relationship relationship = new Relationship(1);
		relationship.setStartDate(Context.getDateFormat().parse("18/02/2012"));
		relationship.setEndDate(Context.getDateFormat().parse("18/02/2001"));
		Map<String, String> map = new HashMap<>();
		MapBindingResult errors = new MapBindingResult(map, Relationship.class.getName());
		new RelationshipValidator().validate(relationship, errors);
		Assert.assertEquals(true, errors.hasErrors());
	}