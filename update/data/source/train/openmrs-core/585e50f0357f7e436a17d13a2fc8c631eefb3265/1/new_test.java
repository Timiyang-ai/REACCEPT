@Test
	@Verifies(value = "should pass for a new concept with a map created with deprecated concept map methods", method = "validate(Object,Errors)")
	public void validate_shouldPassForANewConceptWithAMapCreatedWithDeprecatedConceptMapMethods() throws Exception {
		ConceptService cs = Context.getConceptService();
		Concept concept = new Concept();
		concept.addName(new ConceptName("test name", Context.getLocale()));
		concept.setConceptClass(new ConceptClass());
		concept.setDatatype(new ConceptDatatype());
		ConceptMap map = new ConceptMap();
		map.setSourceCode("unique code");
		map.setSource(cs.getConceptSource(1));
		concept.addConceptMapping(map);
		ValidateUtil.validate(concept);
	}