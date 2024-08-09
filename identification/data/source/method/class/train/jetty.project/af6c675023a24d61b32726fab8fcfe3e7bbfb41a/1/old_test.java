@Test
    public void testDelete()
        throws Exception
    {
        Server server = new Server();

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/test");
        context.setServer(server);

        DefaultSessionCacheFactory cacheFactory = new DefaultSessionCacheFactory();
        cacheFactory.setEvictionPolicy(SessionCache.NEVER_EVICT);
        DefaultSessionCache cache = (DefaultSessionCache)cacheFactory.getSessionCache(context.getSessionHandler());

        TestSessionDataStore store = new TestSessionDataStore();
        cache.setSessionDataStore(store);
        context.getSessionHandler().setSessionCache(cache);
        context.start();

        //test remove non-existent session
        Session session = cache.delete("1234");
        assertNull(session);

        //test remove of existing session in store only
        long now = System.currentTimeMillis();
        SessionData data = store.newSessionData("1234", now - 20, now - 10, now - 20, TimeUnit.MINUTES.toMillis(10));
        store.store("1234", data);
        session = cache.delete("1234");
        assertNotNull(session);
        assertFalse(store.exists("1234"));
        assertFalse(cache.contains("1234"));

        //test remove of session in both store and cache
        data = store.newSessionData("1234", now - 20, now - 10, now - 20, TimeUnit.MINUTES.toMillis(10));
        session = cache.newSession(data);
        cache.put("1234", session);
        assertTrue(store.exists("1234"));
        assertTrue(cache.contains("1234"));
        session = cache.delete("1234");
        assertNotNull(session);
        assertFalse(store.exists("1234"));
        assertFalse(cache.contains("1234"));
    }