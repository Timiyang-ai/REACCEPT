	@Test
	public void handle_shouldNotFailIfTagsIsNull() {
		ConceptNameSaveHandler handler = new ConceptNameSaveHandler();
		ConceptName name = new ConceptName();
		name.setTags(null);
		handler.handle(name, null, null, null);
	}