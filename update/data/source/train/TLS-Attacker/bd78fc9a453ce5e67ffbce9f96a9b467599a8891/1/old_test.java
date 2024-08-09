@Test
    public void testCollectResults() {
        TestVector t = new TestVector(new WorkflowTrace(), null, null, ExecutorType.TLS, null);
        Result r = agent.collectResults(new File("../resources/EvolutionaryFuzzer/AFLTest/graph.trace"), t);
        assertTrue("Failure: Test result should have exactly 4 Vertices",
                r.getBranchTrace().getVerticesSet().size() == 4);
        assertTrue("Failure: Test result should have exactly 6 Edges", r.getBranchTrace().getEdgeMap().size() == 6);
    }