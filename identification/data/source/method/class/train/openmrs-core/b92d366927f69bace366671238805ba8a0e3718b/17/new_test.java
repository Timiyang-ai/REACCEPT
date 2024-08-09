	@Test
	public void getName_shouldNotFailIfNoNamesAreDefined() {
		Concept concept = new Concept();
		Assert.assertNull(concept.getName(new Locale("en"), false));
		Assert.assertNull(concept.getName(new Locale("en"), true));
	}