@Test
    public void testDestroy() {
        RegistryDirectory registryDirectory = getRegistryDirectory();

        List<URL> serviceUrls = new ArrayList<URL>();
        serviceUrls.add(SERVICEURL.addParameter("methods", "getXXX1"));
        serviceUrls.add(SERVICEURL2.addParameter("methods", "getXXX1,getXXX2"));
        serviceUrls.add(SERVICEURL3.addParameter("methods", "getXXX1,getXXX2,getXXX3"));

        registryDirectory.notify(serviceUrls);
        List<Invoker> invokers = registryDirectory.list(invocation);
        Assertions.assertEquals(true, registryDirectory.isAvailable());
        Assertions.assertEquals(true, invokers.get(0).isAvailable());

        registryDirectory.destroy();
        Assertions.assertEquals(false, registryDirectory.isAvailable());
        Assertions.assertEquals(false, invokers.get(0).isAvailable());
        registryDirectory.destroy();

        List<Invoker<RegistryDirectoryTest>> cachedInvokers = registryDirectory.getInvokers();
        Map<String, Invoker<RegistryDirectoryTest>> urlInvokerMap = registryDirectory.getUrlInvokerMap();

        Assertions.assertTrue(cachedInvokers == null);
        Assertions.assertEquals(0, urlInvokerMap.size());
        // List<U> urls = mockRegistry.getSubscribedUrls();

        RpcInvocation inv = new RpcInvocation();
        try {
            registryDirectory.list(inv);
            fail();
        } catch (RpcException e) {
            Assertions.assertTrue(e.getMessage().contains("already destroyed"));
        }
    }