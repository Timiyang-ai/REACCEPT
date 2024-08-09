@Test
    public void testRecalculate() {
        DefaultRebalancer rebalancer = new DefaultRebalancer();
        assertNotNull(rebalancer);

        Integer server1 = "dns1".hashCode();
        OperationsServerLoadHistory server1History = new OperationsServerLoadHistory(MAX_HISTORY_TIME_LIVE);
        server1History.addOpsServerLoad(new LoadInfo(10));

        Integer server2 = "dns2".hashCode();
        OperationsServerLoadHistory server2History = new OperationsServerLoadHistory(MAX_HISTORY_TIME_LIVE);
        server2History.addOpsServerLoad(new LoadInfo(30));

        Integer server3 = "dns3".hashCode();
        OperationsServerLoadHistory server3History = new OperationsServerLoadHistory(MAX_HISTORY_TIME_LIVE);
        server3History.addOpsServerLoad(new LoadInfo(55));

        Map<Integer, OperationsServerLoadHistory> serversHistory = new HashMap<>();

        serversHistory.put(server1, server1History);
        serversHistory.put(server2, server2History);
        serversHistory.put(server3, server3History);
        Map<Integer, RedirectionRule> rules = rebalancer.recalculate(serversHistory);
        assertNotNull(rules);

        assertEquals(1, rules.size());

        assertNotNull(rules.get(server3));

        assertEquals(server1.intValue(), rules.get(server3).getAccessPointId());
        assertEquals(new Double(0.2), new Double(rules.get(server3).getRedirectionProbability()));
    }