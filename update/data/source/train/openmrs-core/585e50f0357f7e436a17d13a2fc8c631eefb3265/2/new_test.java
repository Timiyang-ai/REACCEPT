@Test
	@Verifies(value = "should pass if the concept has atleast one fully specified name added to it", method = "validate(Object,Errors)")
	public void validate_shouldPassIfTheConceptHasAtleastOneFullySpecifiedNameAddedToIt() throws Exception {
		Concept concept = new Concept();
		concept.addName(new ConceptName("one name", Context.getLocale()));
		concept.setConceptClass(new ConceptClass());
		concept.setDatatype(new ConceptDatatype());
		Errors errors = new BindException(concept, "concept");
		new ConceptValidator().validate(concept, errors);
		Assert.assertEquals(false, errors.hasErrors());
	}