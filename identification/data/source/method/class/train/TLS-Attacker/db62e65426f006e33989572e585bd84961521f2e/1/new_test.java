@Test
    public void testCollectResults() {
	TestVector t = new TestVector(null, null, null);
	agent.collectResults(new File("../resources/testsuite/EvolutionaryFuzzer/PinTest/test.trace"), t, t);
    }