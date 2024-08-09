@Test
    public void testCollectResults() {
	WorkflowTrace t = new WorkflowTrace();
	agent.collectResults(new File("../resources/testsuite/EvolutionaryFuzzer/PinTest/test.trace"), t, t);
    }