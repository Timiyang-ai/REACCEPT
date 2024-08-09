private void clientClientMessage() throws Exception {
        Ignite ignite = grid(CLIENT_NODE_IDX);

        ClusterGroup grp = ignite.cluster().forClients();

        assert !grp.nodes().isEmpty();

        registerListenerAndSendMessages(ignite, grp);
    }