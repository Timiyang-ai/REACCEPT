@Test
    public void testSubscribe() {
        // verify lisener.
        final AtomicReference<URL> args = new AtomicReference<URL>();
        registry.subscribe(consumerUrl, new NotifyListener() {

            @Override
            public void notify(List<URL> urls) {
                // FIXME assertEquals(MulticastRegistry.this.service, service);
                args.set(urls.get(0));
            }
        });
        assertEquals(serviceUrl.toFullString(), args.get().toFullString());
        Map<URL, Set<NotifyListener>> arg = registry.getSubscribed();
        assertEquals(consumerUrl, arg.keySet().iterator().next());

    }