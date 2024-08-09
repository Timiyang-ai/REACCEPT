@Test public void testUnlockAndFreeze() throws Exception {
        new File("lock_server_timestamp.dat").delete();
        server = SimulatingServerProxy.newProxyInstance(LockService.class, LockServiceImpl.create(
                new LockServerOptions() {
                    private static final long serialVersionUID = 1L;
                    @Override public TimeDuration getMaxAllowedClockDrift() {
                        return SimpleTimeDuration.of(0, TimeUnit.MILLISECONDS);
                    }
                }), 10);

        LockRequest request = LockRequest.builder(ImmutableSortedMap.of(lock1, LockMode.WRITE))
                .timeoutAfter(SimpleTimeDuration.of(1, TimeUnit.SECONDS)).doNotBlock().build();
        HeldLocksToken token = server.lockWithFullLockResponse(LockClient.ANONYMOUS, request).getToken();
        Assert.assertNotNull(token);
        Assert.assertEquals(LockClient.ANONYMOUS, token.getClient());
        Assert.assertEquals(LockCollections.of(ImmutableSortedMap.of(lock1, LockMode.WRITE)), token.getLockDescriptors());
        try {
            server.unlockAndFreeze(token);
            Assert.fail();
        } catch (IllegalArgumentException expected) {
            /* Expected: anonymous clients can't unlock and freeze */
        }
        server.unlock(token);
        Assert.assertTrue(server.getTokens(client).isEmpty());

        token = server.lockWithFullLockResponse(client, request).getToken();
        HeldLocksToken token2 = server.lockWithFullLockResponse(client, request).getToken();
        Assert.assertNotNull(token2);
        Assert.assertEquals(client, token2.getClient());
        Assert.assertEquals(LockCollections.of(ImmutableSortedMap.of(lock1, LockMode.WRITE)), token2.getLockDescriptors());
        server.unlockAndFreeze(token2);
        token2 = server.lockWithFullLockResponse(client, request).getToken();
        Assert.assertNull(token2);
        server.unlockAndFreeze(token);
        Assert.assertTrue(server.getTokens(client).isEmpty());

        token = server.lockWithFullLockResponse(client, request).getToken();
        Assert.assertNotNull(token);
        token2 = server.lockWithFullLockResponse(client, request).getToken();
        Assert.assertNotNull(token2);
        HeldLocksToken token3 = server.lockWithFullLockResponse(client, request).getToken();
        Assert.assertNotNull(token3);
        server.unlockAndFreeze(token3);
        token3 = server.lockWithFullLockResponse(client, request).getToken();
        Assert.assertNull(token3);
        Assert.assertTrue(server.getTokens(client).isEmpty());
        HeldLocksToken token4 = server.lockWithFullLockResponse(client, LockRequest.builder(ImmutableSortedMap.of(
                lock2, LockMode.WRITE)).doNotBlock().build()).getToken();
        Assert.assertNotNull(token4);
        Assert.assertEquals(ImmutableSet.of(token4), server.getTokens(client));
        token = server.lockWithFullLockResponse(client, request).getToken();
        Assert.assertNull(token);
        Thread.sleep(1000);
        token = server.lockWithFullLockResponse(client, request).getToken();
        Assert.assertNotNull(token);
    }