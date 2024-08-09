static void withClusterConfiguration(Configuration configuration, String haNamedService, List<String> namedNodes, int replicationFactor) {
        configuration.set(DFSConfigKeys.DFS_REPLICATION_KEY, Integer.toString(replicationFactor));
        configuration.set(DFSConfigKeys.DFS_NAMESERVICES, haNamedService);
        configuration.set(
                DFSUtil.addKeySuffixes(DFS_HA_NAMENODES_KEY_PREFIX, haNamedService),
                nodeToString(namedNodes.stream().map(HaConfigurationBuilder::nodeToString).collect(Collectors.joining(",")))
        );

        namedNodes.forEach(nodeName ->
                configuration.set(
                        DFSUtil.addKeySuffixes(DFS_NAMENODE_RPC_ADDRESS_KEY, haNamedService, nodeToString(nodeName)),
                        nodeName)
        );

        configuration.set(DFS_CLIENT_FAILOVER_PROXY_PROVIDER_KEY_PREFIX + "." + haNamedService, ConfiguredFailoverProxyProvider.class.getName());

        configuration.set(HFDS_FS, "hdfs://" + haNamedService);

    }