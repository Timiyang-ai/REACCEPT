@Test
    public void testFailedServer_allDisconnected_from_b_perspective() {
        final String localEndpoint = "b";
        CompleteGraphAdvisor advisor = new CompleteGraphAdvisor(localEndpoint);

        ClusterState clusterState = buildClusterState(
                localEndpoint,
                ImmutableList.of(),
                NodeState.getUnavailableNodeState("a"),
                nodeState("b", epoch, OK, OK, OK),
                NodeState.getUnavailableNodeState("c")
        );

        Optional<NodeRank> failedServer = advisor.failedServer(clusterState);
        assertTrue(failedServer.isPresent());
        assertEquals(new NodeRank("c", 0), failedServer.get());
    }