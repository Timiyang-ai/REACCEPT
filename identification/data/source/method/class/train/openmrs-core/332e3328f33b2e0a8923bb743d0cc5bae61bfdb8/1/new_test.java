@Test
	@Verifies(value = "should not fail on update method with no arguments", method = "before(Method,null,Object)")
	public void before_shouldNotFailOnUpdateMethodWithNoArguments() throws Throwable {
		Method method = ConceptServiceImpl.class.getMethod("updateConceptWords", (Class[]) null);
		requiredDataAdvice.before(method, null, new ConceptServiceImpl());
		requiredDataAdvice.before(method, new Object[] {}, new ConceptServiceImpl());
	}