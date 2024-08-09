	@Test //TRUNK-4967
	public void isConceptNameDuplicate_shouldNotFailIfConceptDoesNotHaveADefaultNameForLocale() {
		//given
		ConceptClass diagnosis = dao.getConceptClasses("Diagnosis").get(0);
		ConceptDatatype na = dao.getConceptDatatypeByName("N/A");

		Concept tuberculosis = new Concept();
		tuberculosis.addName(new ConceptName("Tuberculosis", Locale.US));
		tuberculosis.setDatatype(na);
		tuberculosis.setConceptClass(diagnosis);
		dao.saveConcept(tuberculosis);

		ConceptName shortName = new ConceptName("TB", Locale.FRANCE);
		shortName.setConceptNameType(ConceptNameType.SHORT);
		tuberculosis.addName(shortName);

		//when
		boolean duplicate = dao.isConceptNameDuplicate(shortName);

		//then
		//no NPE exception thrown
		assertThat(duplicate, is(false));
	}