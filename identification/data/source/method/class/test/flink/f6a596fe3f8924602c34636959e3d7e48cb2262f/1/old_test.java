	@Test
	public void wrapHook() throws Exception {
		final String id = "id";

		Thread thread = Thread.currentThread();
		final ClassLoader originalClassLoader = thread.getContextClassLoader();
		final ClassLoader userClassLoader = new URLClassLoader(new URL[0]);

		final Runnable command = spy(new Runnable() {
			@Override
			public void run() {
				assertEquals(userClassLoader, Thread.currentThread().getContextClassLoader());
			}
		});

		MasterTriggerRestoreHook<String> hook = spy(new MasterTriggerRestoreHook<String>() {
			@Override
			public String getIdentifier() {
				assertEquals(userClassLoader, Thread.currentThread().getContextClassLoader());
				return id;
			}

			@Override
			public void reset() throws Exception {
				assertEquals(userClassLoader, Thread.currentThread().getContextClassLoader());
			}

			@Override
			public void close() throws Exception {
				assertEquals(userClassLoader, Thread.currentThread().getContextClassLoader());
			}

			@Nullable
			@Override
			public CompletableFuture<String> triggerCheckpoint(long checkpointId, long timestamp, Executor executor) throws Exception {
				assertEquals(userClassLoader, Thread.currentThread().getContextClassLoader());
				executor.execute(command);
				return null;
			}

			@Override
			public void restoreCheckpoint(long checkpointId, @Nullable String checkpointData) throws Exception {
				assertEquals(userClassLoader, Thread.currentThread().getContextClassLoader());
			}

			@Nullable
			@Override
			public SimpleVersionedSerializer<String> createCheckpointDataSerializer() {
				assertEquals(userClassLoader, Thread.currentThread().getContextClassLoader());
				return null;
			}
		});

		MasterTriggerRestoreHook<String> wrapped = MasterHooks.wrapHook(hook, userClassLoader);

		// verify getIdentifier
		wrapped.getIdentifier();
		verify(hook, times(1)).getIdentifier();
		assertEquals(originalClassLoader, thread.getContextClassLoader());

		// verify triggerCheckpoint and its wrapped executor
		TestExecutor testExecutor = new TestExecutor();
		wrapped.triggerCheckpoint(0L, 0, testExecutor);
		assertEquals(originalClassLoader, thread.getContextClassLoader());
		assertNotNull(testExecutor.command);
		testExecutor.command.run();
		verify(command, times(1)).run();
		assertEquals(originalClassLoader, thread.getContextClassLoader());

		// verify restoreCheckpoint
		wrapped.restoreCheckpoint(0L, "");
		verify(hook, times(1)).restoreCheckpoint(eq(0L), eq(""));
		assertEquals(originalClassLoader, thread.getContextClassLoader());

		// verify createCheckpointDataSerializer
		wrapped.createCheckpointDataSerializer();
		verify(hook, times(1)).createCheckpointDataSerializer();
		assertEquals(originalClassLoader, thread.getContextClassLoader());

		// verify close
		wrapped.close();
		verify(hook, times(1)).close();
		assertEquals(originalClassLoader, thread.getContextClassLoader());
	}