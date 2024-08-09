@Test
	@Verifies(value = "should not fail on update method with no arguments", method = "before(Method,null,Object)")
	public void before_shouldNotFailOnUpdateMethodWithNoArguments() throws Throwable {
		Method method = ConceptServiceImpl.class.getMethod("updateConceptWords", (Class[]) null);
		new RequiredDataAdvice().before(method, null, new ConceptServiceImpl());
		new RequiredDataAdvice().before(method, new Object[] {}, new ConceptServiceImpl());
	}