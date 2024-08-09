@Test
    public void testRecalculate() {
        DefaultRebalancer rebalancer = new DefaultRebalancer();
        assertNotNull(rebalancer);

        String server1 = "dns1";
        OperationsServerLoadHistory server1History = new OperationsServerLoadHistory(MAX_HISTORY_TIME_LIVE);
        server1History.addOpsServerLoad(0, 10, 0);
        Map<ZkChannelType, OperationsServerLoadHistory> server1OpsHistory = new HashMap<>();
        server1OpsHistory.put(ZkChannelType.HTTP, server1History);

        String server2 = "dns2";
        OperationsServerLoadHistory server2History = new OperationsServerLoadHistory(MAX_HISTORY_TIME_LIVE);
        server2History.addOpsServerLoad(0, 30, 0);
        Map<ZkChannelType, OperationsServerLoadHistory> server2OpsHistory = new HashMap<>();
        server2OpsHistory.put(ZkChannelType.HTTP, server2History);

        String server3 = "dns3";
        OperationsServerLoadHistory server3History = new OperationsServerLoadHistory(MAX_HISTORY_TIME_LIVE);
        server3History.addOpsServerLoad(0, 55, 0);
        Map<ZkChannelType, OperationsServerLoadHistory> server3OpsHistory = new HashMap<>();
        server3OpsHistory.put(ZkChannelType.HTTP, server3History);

        Map<String, Map<ZkChannelType, OperationsServerLoadHistory>> serversHistory = new HashMap<>();

        serversHistory.put(server1, server1OpsHistory);
        serversHistory.put(server2, server2OpsHistory);
        serversHistory.put(server3, server3OpsHistory);
        Map<String,RedirectionRule> rules = rebalancer.recalculate(serversHistory );
        assertNotNull(rules);

        assertEquals(1, rules.size());

        assertNotNull(rules.get(server3));

        assertEquals(server1, rules.get(server3).getDnsName());
        assertEquals(new Double(0.2), new Double(rules.get(server3).getRedirectionProbability()));
    }