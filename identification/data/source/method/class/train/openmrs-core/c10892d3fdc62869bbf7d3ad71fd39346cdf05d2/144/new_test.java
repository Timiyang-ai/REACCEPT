	@Test
	public void saveConceptSource_shouldNotSetCreatorIfOneIsSuppliedAlready() {
		User expectedCreator = new User(501); // a user that isn't logged in now
		
		ConceptSource newConceptSource = new ConceptSource();
		newConceptSource.setName("name");
		newConceptSource.setDescription("desc");
		newConceptSource.setHl7Code("hl7Code");
		newConceptSource.setCreator(expectedCreator);
		Context.getConceptService().saveConceptSource(newConceptSource);
		
		Assert.assertEquals(newConceptSource.getCreator(), expectedCreator);
	}