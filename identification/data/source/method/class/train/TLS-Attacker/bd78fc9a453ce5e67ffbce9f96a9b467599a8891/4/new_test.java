@Test
    public void testOnApply() {
        AgentResult result = new AgentResult(false, true, 9, 10, new BranchTrace(), new TestVector(new WorkflowTrace(), null,
                null, ExecutorType.TLS, null), "unit3.test", null);
        rule.onApply(result);
        assertTrue(new File(config.getOutputFolder() + rule.getConfig().getOutputFolder()).listFiles().length == 1);

    }