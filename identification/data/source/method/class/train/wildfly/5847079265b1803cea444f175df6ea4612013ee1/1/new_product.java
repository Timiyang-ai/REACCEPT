private void loadbalance(EJBDirectory directory, String deployment1, String deployment2) throws Exception {
        this.start(CONTAINER_1);
        this.deploy(CONTAINER_1, deployment1);
        this.start(CONTAINER_2);
        this.deploy(CONTAINER_2, deployment2);

        final ContextSelector<EJBClientContext> previousSelector = EJBClientContextSelector.setup(CLIENT_PROPERTIES);

        int numberOfServers = 2;
        int numberOfCalls = 40;
        // there will be at least 20% of calls processed by all servers
        double serversProccessedAtLeast = 0.2;

        try {
            StatelessRemoteHome home = directory.lookupHome(StatelessBean.class, StatelessRemoteHome.class);
            StatelessRemote bean = home.create();

            String node = bean.getNodeName();
            log.info("Node called : " + node);

            validateBalancing(bean, numberOfCalls, numberOfServers, serversProccessedAtLeast);

            Properties contextChangeProperties = new Properties();
            contextChangeProperties.put(REMOTE_PORT_PROPERTY_NAME, PORT_2.toString());
            contextChangeProperties.put(REMOTE_HOST_PROPERTY_NAME, HOST_2.toString());
            EJBClientContextSelector.setup(CLIENT_PROPERTIES, contextChangeProperties);

            bean = home.create();
            node = bean.getNodeName();
            log.info("Node called : " + node);

            validateBalancing(bean, numberOfCalls, numberOfServers, serversProccessedAtLeast);
        } finally {
            // reset the selector
            if (previousSelector != null) {
                EJBClientContext.setSelector(previousSelector);
            }
            // undeploy&shutdown the containers
            undeployAll();
            shutdownAll();
        }
    }