    @Test
    public void withClusterConfiguration() {
        // given
        Configuration configuration = new Configuration();
        String haClusterName = "haCluster";
        List<String> namedNodes = Arrays.asList("kerb_node_01.example.com:8021", "kerb_node_02.example.com:8022");
        int replicationFactor = 3;

        // when
        HaConfigurationBuilder.withClusterConfiguration(configuration, haClusterName, namedNodes, replicationFactor);

        // then
        assertThat(configuration, notNullValue());
        assertThat(configuration.get(DFSConfigKeys.DFS_REPLICATION_KEY), is("3"));
        assertThat(configuration.get(DFSConfigKeys.DFS_NAMESERVICES), is("haCluster"));
        assertThat(configuration.get("dfs.ha.namenodes.haCluster"), is("kerb_node_01_example_com,kerb_node_02_example_com"));
        assertThat(configuration.get("dfs.namenode.rpc-address.haCluster.kerb_node_01_example_com"), is("kerb_node_01.example.com:8021"));
        assertThat(configuration.get("dfs.namenode.rpc-address.haCluster.kerb_node_02_example_com"), is("kerb_node_02.example.com:8022"));
        assertThat(configuration.get("dfs.client.failover.proxy.provider.haCluster"), is("org.apache.hadoop.hdfs.server.namenode.ha.ConfiguredFailoverProxyProvider"));
        assertThat(configuration.get("fs.defaultFS"), is("hdfs://haCluster"));
    }