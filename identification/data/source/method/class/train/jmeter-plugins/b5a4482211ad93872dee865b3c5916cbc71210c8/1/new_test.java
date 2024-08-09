@Test
    public void testAddMetric() {
        System.out.println("addMetric");
        String metric = "";
        String params = "";
        PerfMonAgentConnector instance = new PerfMonAgentConnectorImpl();
        instance.addMetric(metric, params, null);
    }