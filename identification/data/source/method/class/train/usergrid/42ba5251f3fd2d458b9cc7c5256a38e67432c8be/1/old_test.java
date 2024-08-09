@Test
	public void testLock() throws InterruptedException, ExecutionException, UGLockException {

		final UUID application = UUID.randomUUID();
		final UUID entity = UUID.randomUUID();

		logger.info("Locking:" + application.toString() + "/" + entity.toString());

		// Lock a node twice to test reentrancy and validate.
		manager.lock(application, entity);
		manager.lock(application, entity);

		boolean wasLocked = lockInDifferentThread(application, entity);
		Assert.assertEquals(false, wasLocked);
		
		// Unlock once
		manager.unlock(application, entity);
		
		// Try from the thread expecting to fail since we still hold one reentrant lock.
		wasLocked = lockInDifferentThread(application, entity);
		Assert.assertEquals(false, wasLocked);
		
		// Unlock completely
		logger.info("Releasing lock:" + application.toString() + "/" + entity.toString());
		manager.unlock(application, entity);
		
		// Try to effectively get the lock from the thread since the current one has already released it.
		wasLocked = lockInDifferentThread(application, entity);
		Assert.assertEquals(true, wasLocked);
	}