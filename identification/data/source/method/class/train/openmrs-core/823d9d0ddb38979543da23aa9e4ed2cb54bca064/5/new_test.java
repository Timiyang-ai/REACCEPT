	@Test
	public void saveDrug_shouldPutGeneratedIdOntoReturnedDrug() {
		Drug drug = new Drug();
		Concept concept = new Concept();
		concept.addName(new ConceptName("Concept", new Locale("en", "US")));
		concept.addDescription(new ConceptDescription("Description", new Locale("en", "US")));
		concept.setConceptClass(new ConceptClass(1));
		concept.setDatatype(new ConceptDatatype(1));
		Concept savedConcept = conceptService.saveConcept(concept);
		drug.setConcept(savedConcept);
		assertNull(drug.getDrugId());
		Drug savedDrug = conceptService.saveDrug(drug);
		assertNotNull(savedDrug.getDrugId());
	}