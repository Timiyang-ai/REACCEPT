	@Test(expected = ConceptNameInUseException.class)
	public void purgeConcept_shouldFailIfAnyOfTheConceptNamesOfTheConceptIsBeingUsedByAnObs() {
		Obs o = new Obs();
		o.setConcept(Context.getConceptService().getConcept(3));
		o.setPerson(new Patient(2));
		o.setEncounter(new Encounter(3));
		o.setObsDatetime(new Date());
		o.setLocation(new Location(1));
		ConceptName conceptName = new ConceptName(1847);
		o.setValueCodedName(conceptName);
		Context.getObsService().saveObs(o, null);
		//ensure that the association between the conceptName and the obs has been established
		Assert.assertEquals(true, conceptService.hasAnyObservation(conceptName));
		
		Concept concept = conceptService.getConceptByName("cd4 count");
		//make sure the name concept name exists
		Assert.assertNotNull(concept);
		conceptService.purgeConcept(concept);
	}