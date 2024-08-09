@Test
    public void testUnregisterService() throws Exception
    {
        final String name = "name";

        final CountDownLatch restartLatch = new CountDownLatch(1);
        List<Closeable> closeables = Lists.newArrayList();

        InstanceSerializer<String> slowSerializer = new JsonInstanceSerializer<String>(String.class)
        {
            private boolean first = true;

            @Override
            public byte[] serialize(ServiceInstance<String> instance) throws Exception
            {
                if ( first )
                {
                    System.out.println("Serializer first registration.");
                    first = false;
                }
                else
                {
                    System.out.println("Waiting for reconnect to finish.");
                    // Simulate the serialize method being slow.
                    // This could just be a timed wait, but that's kind of non-deterministic.
                    restartLatch.await();
                }
                return super.serialize(instance);
            }
        };

        try
        {
            CuratorFramework client = CuratorFrameworkFactory.newClient(server.getConnectString(), new RetryOneTime(1));
            closeables.add(client);
            client.start();

            ServiceInstance<String> instance = ServiceInstance.<String>builder().payload("thing").name(name).port(10064).build();
            ServiceDiscovery<String> discovery = ServiceDiscoveryBuilder.builder(String.class).basePath("/test").client(client).thisInstance(instance).serializer(slowSerializer).watchInstances(true).build();
            closeables.add(discovery);
            discovery.start();

            Assert.assertFalse(discovery.queryForInstances(name).isEmpty(), "Service should start registered.");

            server.stop();
            server.restart();

            discovery.unregisterService(instance);
            restartLatch.countDown();

            new Timing().sleepABit(); // Wait for the rest of registration to finish.

            Assert.assertTrue(discovery.queryForInstances(name).isEmpty(), "Service should have unregistered.");
        }
        finally
        {
            Collections.reverse(closeables);
            for ( Closeable c : closeables )
            {
                CloseableUtils.closeQuietly(c);
            }
        }
    }