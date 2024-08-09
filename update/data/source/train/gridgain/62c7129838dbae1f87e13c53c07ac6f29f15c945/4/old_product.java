private void clientServerMessage(boolean async) throws Exception {
        Ignite ignite = grid(CLIENT_NODE_IDX);

        ClusterGroup grp = ignite.cluster().forServers();

        assert !grp.nodes().isEmpty();

        registerListenerAndSendMessages(ignite, grp, async);
    }