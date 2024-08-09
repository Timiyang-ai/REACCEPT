@Test
    public void testDestroy() {
        RegistryDirectory registryDirectory = getRegistryDirectory();

        List<URL> serviceUrls = new ArrayList<URL>();
        serviceUrls.add(SERVICEURL.addParameter("methods", "getXXX1"));
        serviceUrls.add(SERVICEURL2.addParameter("methods", "getXXX1,getXXX2"));
        serviceUrls.add(SERVICEURL3.addParameter("methods", "getXXX1,getXXX2,getXXX3"));

        registryDirectory.notify(serviceUrls);
        List<Invoker> invokers = registryDirectory.list(invocation);
        Assertions.assertTrue(registryDirectory.isAvailable());
        Assertions.assertTrue(invokers.get(0).isAvailable());

        registryDirectory.destroy();
        Assertions.assertFalse(registryDirectory.isAvailable());
        Assertions.assertFalse(invokers.get(0).isAvailable());
        registryDirectory.destroy();

        List<Invoker<RegistryDirectoryTest>> cachedInvokers = registryDirectory.getInvokers();
        Map<String, Invoker<RegistryDirectoryTest>> urlInvokerMap = registryDirectory.getUrlInvokerMap();

        Assertions.assertNull(cachedInvokers);
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