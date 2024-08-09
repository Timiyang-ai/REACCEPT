@Test public void testLogCurrentState() throws Exception {
        long lockTimeoutMs = LockRequest.DEFAULT_LOCK_TIMEOUT.toMillis();
        // Timeout in private LockServiceImpl.LOG_STATE_DEBUG_LOCK_WAIT_TIME_IN_MILLIS; test value is double that
        long logCurrentStateCallTimeoutMs = 2 * 5000L;

        // First lock request grabs a READ lock
        LockRequest request1 = LockRequest.builder(ImmutableSortedMap.of(lock1, LockMode.READ))
                .doNotBlock().build();
        long currentTimeMs = System.currentTimeMillis();
        LockResponse response1 = server.lockWithFullLockResponse(LockClient.ANONYMOUS, request1);
        Assert.assertTrue(response1.success());
        Assert.assertTrue(response1.getLockHolders().isEmpty());
        HeldLocksToken token1 = response1.getToken();
        Assert.assertNotNull(token1);
        Assert.assertEquals(LockClient.ANONYMOUS, token1.getClient());
        Assert.assertEquals(request1.getLockDescriptors(), token1.getLockDescriptors());
        Assert.assertTrue(currentTimeMs + lockTimeoutMs <= token1.getExpirationDateMs());
        Assert.assertTrue(token1.getExpirationDateMs()
                <= System.currentTimeMillis() + lockTimeoutMs);

        // Second request grabs corresponding WRITE lock, will block inside LockServer until READ lock expires
        executor.submit(new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                barrier.await();
                LockRequest request2 = LockRequest.builder(ImmutableSortedMap.of(lock1, LockMode.WRITE)).build();
                LockResponse response2 = server.lockWithFullLockResponse(LockClient.ANONYMOUS, request2);
                HeldLocksToken validToken = response2.getToken();
                Assert.assertNotNull(validToken);
                server.unlock(validToken);
                return null;
            }
        });

        /* Now make the logCurrentState() request; with the WRITE lock request blocked inside LockServer.lock(),
         * this call should block until the first of these happens:
         * -The READ lock times out and the WRITE lock can be granted, thus freeing up the debugLock
         * -The logCurrentState tryLock() call times out after LOG_STATE_DEBUG_LOCK_WAIT_TIME_IN_MILLIS
         *   and the call moves on to logCurrentStateInconsistent()
         */
        barrier.await();
        Thread.sleep(500);
        Future<?> logCallFuture = executor.submit(new Callable<Void>() {
            @Override
            public Void call() {
                server.logCurrentState();
                return null;
            }
        });

        try {
            logCallFuture.get(logCurrentStateCallTimeoutMs, TimeUnit.MILLISECONDS);
        } catch (TimeoutException e) {
            // If we exceed the timeout, the call is hung and it's a failure
            Assert.fail();
        }
    }