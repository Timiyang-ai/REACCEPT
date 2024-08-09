@Test
	public void visitObject() throws Exception
	{
		ClassVisitFilter<String> filter = new ClassVisitFilter<String>(String.class);
		assertTrue(filter.visitObject("a string"));
		assertFalse(filter.visitObject(123));

		filter = new ClassVisitFilter<String>(null);
		assertTrue(filter.visitObject("a string"));
		assertTrue(filter.visitObject(123));
	}