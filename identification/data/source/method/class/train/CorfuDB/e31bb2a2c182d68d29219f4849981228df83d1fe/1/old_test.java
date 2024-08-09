    @Test
    public void isReady() {
        final String localEndpoint = "a";
        final long epoch1 = 1;
        final long epoch2 = 2;

        ClusterState invalidClusterState = ClusterState.buildClusterState(
                localEndpoint,
                ImmutableList.of(),
                nodeState("a", epoch1, OK),
                nodeState("b", epoch2, OK)
        );
        assertThat(invalidClusterState.isReady()).isFalse();

        ClusterState validClusterState = ClusterState.buildClusterState(
                localEndpoint,
                ImmutableList.of(),
                nodeState("a", epoch1, OK),
                nodeState("b", epoch1, OK)
        );
        assertThat(validClusterState.isReady()).isTrue();
    }