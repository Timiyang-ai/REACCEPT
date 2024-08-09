@Test
    public void testLoadbalance() throws Exception {
        this.start(0);
        this.deploy(0);
        this.start(1);
        this.deploy(1);

        final ContextSelector<EJBClientContext> previousSelector = EJBClientContextSelector.setup(CLIENT_PROPERTIES);

        int numberOfServers = 2;
        int numberOfCalls = 50;
        // there will be at least 20% of calls processed by all servers
        double serversProccessedAtLeast = 0.2;

        try {
            ViewChangeListener listener = context.lookupStateless(ViewChangeListenerBean.class, ViewChangeListener.class);
            
            this.establishView(listener, NODES);
            
            Stateless bean = context.lookupStateless(StatelessBean.class, Stateless.class);

            String node = bean.getNodeName();
            log.info("Node called : " + node);

            validateBalancing(bean, numberOfCalls, numberOfServers, serversProccessedAtLeast);

            Properties contextChangeProperties = new Properties();
            contextChangeProperties.put(REMOTE_PORT_PROPERTY_NAME, PORT_2.toString());
            contextChangeProperties.put(REMOTE_HOST_PROPERTY_NAME, HOST_2.toString());
            EJBClientContextSelector.setup(CLIENT_PROPERTIES, contextChangeProperties);

            bean = context.lookupStateless(StatelessBean.class, Stateless.class);
            node = bean.getNodeName();
            log.info("Node called : " + node);

            validateBalancing(bean, numberOfCalls, numberOfServers, serversProccessedAtLeast);

            this.stop(0);
            
            this.establishView(listener, NODES[1]);
            
            node = bean.getNodeName();
            log.info("Node called : " + node);

            this.start(0);
            
            this.establishView(listener, NODES);
            
            node = bean.getNodeName();
            log.info("Node called : " + node);

            validateBalancing(bean, numberOfCalls, numberOfServers, serversProccessedAtLeast);
        } finally {
            // reset the selector
            if (previousSelector != null) {
                EJBClientContext.setSelector(previousSelector);
            }
            // shutdown the containers
            for (int i = 0; i < NODES.length; ++i) {
                this.undeploy(i);
                this.stop(i);
            }
        }
    }