@Test
    public void testCollectResults() {
	TestVector t = new TestVector(null, null, null, ExecutorType.TLS, null);
	agent.collectResults(new File("../resources/EvolutionaryFuzzer/PinTest/test.trace"), t);
    }