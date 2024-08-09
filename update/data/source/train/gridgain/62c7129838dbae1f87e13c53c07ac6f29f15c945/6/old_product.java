private void clientClientMessage(boolean async) throws Exception {
        Ignite ignite = grid(CLIENT_NODE_IDX);

        ClusterGroup grp = ignite.cluster().forClients();

        assert !grp.nodes().isEmpty();

        registerListenerAndSendMessages(ignite, grp, async);
    }