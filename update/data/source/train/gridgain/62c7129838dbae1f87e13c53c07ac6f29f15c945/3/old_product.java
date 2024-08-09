private void serverClientMessage(boolean async) throws Exception {
        Ignite ignite = grid(SERVER_NODE_IDX);

        ClusterGroup grp = ignite.cluster().forClients();

        assert !grp.nodes().isEmpty();

        registerListenerAndSendMessages(ignite, grp, async);
    }