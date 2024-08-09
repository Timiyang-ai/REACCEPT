@Test
    public void testFailedServer_allDisconnected_from_b_perspective() {
        final String localEndpoint = "b";
        CompleteGraphAdvisor advisor = new CompleteGraphAdvisor(localEndpoint);

        ClusterState clusterState = buildClusterState(
                localEndpoint,
                NodeState.getUnavailableNodeState("a"),
                nodeState("b", OK, OK, OK),
                NodeState.getUnavailableNodeState("c")
        );

        List<String> unresponsiveServers = new ArrayList<>();
        Optional<NodeRank> failedServer = advisor.failedServer(clusterState, unresponsiveServers);
        assertTrue(failedServer.isPresent());
        assertEquals(new NodeRank("c", 0), failedServer.get());
    }