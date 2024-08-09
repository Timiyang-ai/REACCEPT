@Test
    public void testGenerateAgent() throws IllegalAgentException {
        EvolutionaryFuzzerConfig config = new EvolutionaryFuzzerConfig();
        config.setAgent("BLIND");
        Agent agent = AgentFactory.generateAgent(config, null);
        assertTrue(agent instanceof BlindAgent);
        config.setAgent("PIN");
        agent = AgentFactory.generateAgent(config, null);
        assertTrue(agent instanceof PINAgent);
        config.setAgent("AFL");
        agent = AgentFactory.generateAgent(config, null);
        assertTrue(agent instanceof AFLAgent);
        config.setAgent("NOT A REAL AGENT");
        try {
            agent = AgentFactory.generateAgent(config, null);
            fail("Undefined Agent did not throw an Exception");
        } catch (IllegalAgentException E) {
            assertTrue(E != null);
        }
    }