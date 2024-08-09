static void withClusterConfiguration(Configuration configuration, List<String> namedNodes, int replicationFactor) {
        configuration.set(DFSConfigKeys.DFS_REPLICATION_KEY, Integer.toString(replicationFactor));
        configuration.set(DFSConfigKeys.DFS_NAMESERVICES, HFDS_NAMED_SERVICE);
        configuration.set(
                DFSUtil.addKeySuffixes(DFS_HA_NAMENODES_KEY_PREFIX, HFDS_NAMED_SERVICE),
                nodeToString(namedNodes.stream().map(HaConfigurationBuilder::nodeToString).collect(Collectors.joining(",")))
        );

        namedNodes.forEach(nodeName ->
                configuration.set(
                        DFSUtil.addKeySuffixes(DFS_NAMENODE_RPC_ADDRESS_KEY, HFDS_NAMED_SERVICE, nodeToString(nodeName)),
                        nodeName)
        );

        configuration.set(DFS_CLIENT_FAILOVER_PROXY_PROVIDER_KEY_PREFIX + "." + HFDS_NAMED_SERVICE, ConfiguredFailoverProxyProvider.class.getName());

        configuration.set(HFDS_FS, "hdfs://" + HFDS_NAMED_SERVICE);

    }