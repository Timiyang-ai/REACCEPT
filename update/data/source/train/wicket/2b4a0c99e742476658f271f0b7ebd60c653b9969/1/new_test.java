@Test
	public void visitStrings() throws Exception
	{
		ClassVisitFilter filter = new ClassVisitFilter(String.class);
		assertTrue(filter.visitObject("a string"));
		assertFalse(filter.visitObject(123));
	}