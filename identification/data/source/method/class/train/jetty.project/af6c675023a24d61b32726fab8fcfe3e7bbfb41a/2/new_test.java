@Test
    public void testRenewSessionId()
        throws Exception
    {
        Server server = new Server();

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/test");
        context.setServer(server);

        DefaultSessionCacheFactory cacheFactory = new DefaultSessionCacheFactory();
        cacheFactory.setEvictionPolicy(SessionCache.NEVER_EVICT);
        DefaultSessionCache cache = (DefaultSessionCache)cacheFactory.getSessionCache(context.getSessionHandler());

        TestSessionDataStore store = new TestSessionDataStore(true);//fake passivation
        cache.setSessionDataStore(store);
        context.getSessionHandler().setSessionCache(cache);

        context.start();

        //put a session in the cache and store
        long now = System.currentTimeMillis();
        SessionData data = store.newSessionData("1234", now - 20, now - 10, now - 20, TimeUnit.MINUTES.toMillis(10));
        Session session = cache.newSession(data);
        cache.add("1234", session);
        assertTrue(cache.contains("1234"));

        cache.renewSessionId("1234", "5678", "1234.foo", "5678.foo");

        assertTrue(cache.contains("5678"));
        assertFalse(cache.contains("1234"));

        assertTrue(store.exists("5678"));
        assertFalse(store.exists("1234"));
    }