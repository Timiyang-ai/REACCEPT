@Test
    public void testOnDecline() {
        AgentResult result = new AgentResult(false, false, 0, 2, null, null, null, null);
        assertNull(result.isGoodTrace());
        rule.onDecline(result);
        assertFalse(result.isGoodTrace());
    }