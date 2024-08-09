@Test
    public void testSubscribe() {
        Logger.getLogger(ControllerTest.class.getName()).info("subscribe");

        final String callback = "http://localhost/doNothing";
        final String topic = "http://feeds.feedburner.com/screaming-penguin";
        final long lease_seconds = -1;
        final String secret = null;
        final String verify_token = "MyVoiceIsMyPassport";
        final HubDAO dao = new InMemoryHubDAO();
        final Notifier notifier = null;
        final FeedFetcher fetcher = new HttpURLFeedFetcher(HashMapFeedInfoCache.getInstance());
        Hub instance = new Hub(dao, new AlwaysVerifier(), notifier, fetcher);

        Boolean result = instance.subscribe(callback, topic, "sync", lease_seconds, secret, verify_token);
        assertEquals(true, result);

        instance = new Hub(dao, new NeverVerifier(), notifier, fetcher);
        result = instance.subscribe(callback, topic, "sync", lease_seconds, secret, verify_token);
        assertEquals(false, result);

        result = instance.subscribe(callback, topic, "async", lease_seconds, secret, verify_token);
        assertEquals(null, result);

        // Test internal assertions
        try {
            instance.subscribe(null, topic, "async", lease_seconds, secret, verify_token);
            fail();
        } catch (final HttpStatusCodeException e) {
            assertEquals(400, e.getStatus());
            Logger.getLogger(ControllerTest.class.getName()).info(e.getMessage());
        }

        try {
            instance.subscribe(callback, null, "async", lease_seconds, secret, verify_token);
            fail();
        } catch (final HttpStatusCodeException e) {
            assertEquals(400, e.getStatus());
            Logger.getLogger(ControllerTest.class.getName()).info(e.getMessage());
        }

        try {
            instance.subscribe(callback, topic, "foo", lease_seconds, secret, verify_token);
            fail();
        } catch (final HttpStatusCodeException e) {
            assertEquals(400, e.getStatus());
            Logger.getLogger(ControllerTest.class.getName()).info(e.getMessage());
        }

        // test general exception
        instance = new Hub(dao, new ExceptionVerifier(), notifier, fetcher);

        try {
            result = instance.subscribe(callback, topic, "sync", lease_seconds, secret, verify_token);
            fail();
        } catch (final HttpStatusCodeException e) {
            assertEquals(500, e.getStatus());
        }
    }