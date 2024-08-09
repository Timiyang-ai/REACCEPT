	@Test
	public void validate_shouldFailIfTheCodeIsAWhiteSpaceCharacter() {
		ConceptReferenceTerm term = new ConceptReferenceTerm();
		term.setName("name");
		term.setCode(" ");
		term.setConceptSource(Context.getConceptService().getConceptSource(1));
		Errors errors = new BindException(term, "term");
		new ConceptReferenceTermValidator().validate(term, errors);
		Assert.assertEquals(true, errors.hasFieldErrors("code"));
	}