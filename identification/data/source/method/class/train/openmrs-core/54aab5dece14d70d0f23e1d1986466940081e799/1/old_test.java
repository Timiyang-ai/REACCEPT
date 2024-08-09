	@Test
	public void equals_shouldNotReturnTrueIfObjIsConcept() {
		ConceptNumeric cn = new ConceptNumeric(123);
		Concept c = new Concept(123);
		
		Assert.assertNotSame(c, cn);
		Assert.assertNotSame(cn, c);
	}