	@Test
	public void getFunction(){
		assertNotNull(FunctionRegistry.getFunction("if"));

		assertNull(FunctionRegistry.getFunction("sin"));
		assertNotNull(FunctionRegistry.getFunction("x-sin"));

		try {
			FunctionRegistry.getFunction(Thread.class.getName());

			fail();
		} catch(TypeCheckException tce){
			// Ignored
		}

		try {
			FunctionRegistry.getFunction(MaliciousThread.class.getName());

			fail();
		} catch(TypeCheckException tce){
			// Ignored
		}

		Function firstEcho = FunctionRegistry.getFunction(EchoFunction.class.getName());
		Function secondEcho = FunctionRegistry.getFunction(EchoFunction.class.getName());

		assertNotSame(firstEcho, secondEcho);

		try {
			FunctionRegistry.getFunction(MaliciousEchoFunction.class.getName());

			fail();
		} catch(EvaluationException ee){
			Throwable cause = ee.getCause();

			assertTrue(cause instanceof ExceptionInInitializerError);
		}
	}