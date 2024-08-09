	@Test
	public void toString_shouldReturnConceptIdIfPresentOrNull() {
		Concept c = new Concept();
		Assert.assertEquals("Concept #null", c.toString());
		c.setId(2);
		Assert.assertEquals("Concept #2", c.toString());
	}