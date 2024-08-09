	@Test
	public void before_shouldNotFailOnUpdateMethodWithNoArguments() throws Throwable {
		Method method = ConceptServiceImpl.class.getMethod("updateConceptIndexes", (Class[]) null);
		requiredDataAdvice.before(method, null, new ConceptServiceImpl());
		requiredDataAdvice.before(method, new Object[] {}, new ConceptServiceImpl());
	}