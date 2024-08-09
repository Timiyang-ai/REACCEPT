	@Test
	public void getConceptDatatypeByUuid_shouldFindObjectGivenValidUuid() {
		String uuid = "8d4a4488-c2cc-11de-8d13-0010c6dffd0f";
		ConceptDatatype conceptDatatype = Context.getConceptService().getConceptDatatypeByUuid(uuid);
		Assert.assertEquals(1, (int) conceptDatatype.getConceptDatatypeId());
	}