@Test
	public void testExecute() throws IOException {
		final Counter counter = new Counter("test html report", null);
		counter.addRequest("test1", 0, 1, false, 1000);
		counter.addRequest("test2", 1000, 900, false, 1000);
		counter.addRequest("test3", 10000, 1000, true, 10000);
		final Timer timer = new Timer("test", true);
		try {
			final Collector collector = new Collector("test", Collections.singletonList(counter),
					timer);
			final String counterName = counter.getName();
			final String sessionId = "sessionId";
			final String threadId = "threadId";

			assertNotNull("message GC", Action.GC.execute(collector, counterName, sessionId,
					threadId));
			assertNotNull("message CLEAR_COUNTER", Action.CLEAR_COUNTER.execute(collector,
					counterName, sessionId, threadId));
			if (CacheManager.getInstance().getCache("test clear") == null) {
				CacheManager.getInstance().addCache("test clear");
			}
			assertNotNull("message CLEAR_CACHES", Action.CLEAR_CACHES.execute(collector,
					counterName, sessionId, threadId));
			final String heapDump1 = Action.HEAP_DUMP.execute(collector, counterName, sessionId,
					threadId);
			assertNotNull("message HEAP_DUMP", heapDump1);
			String heapDump2;
			// on le refait une deuxième fois dans la même seconde pour tester le nom du fichier
			do {
				heapDump2 = Action.HEAP_DUMP.execute(collector, counterName, sessionId, threadId);
				assertNotNull("message HEAP_DUMP", heapDump2);
			} while (heapDump1.equals(heapDump2));
			for (final File file : Parameters.TEMPORARY_DIRECTORY.listFiles()) {
				if (!file.isDirectory() && file.getName().startsWith("heapdump") && !file.delete()) {
					file.deleteOnExit();
				}
			}
			assertNotNull("message INVALIDATE_SESSIONS", Action.INVALIDATE_SESSIONS.execute(
					collector, counterName, sessionId, threadId));
			assertNotNull("message INVALIDATE_SESSION", Action.INVALIDATE_SESSION.execute(
					collector, counterName, sessionId, threadId));

			try {
				assertNull("message KILL_THREAD", Action.KILL_THREAD.execute(collector,
						counterName, sessionId, threadId));
			} catch (final IllegalArgumentException e) {
				assertNotNull(e.toString(), e);
			}
			assertNull("message KILL_THREAD", Action.KILL_THREAD.execute(collector, counterName,
					sessionId, "nopid_noip_id"));
			assertNull("message KILL_THREAD", Action.KILL_THREAD.execute(collector, counterName,
					sessionId, PID.getPID() + "_noip_id"));
			final Thread myThread = new Thread(new Runnable() {
				/** {@inheritDoc} */
				public void run() {
					try {
						Thread.sleep(10000);
					} catch (final InterruptedException e) {
						throw new RuntimeException(e);
					}
				}
			});
			myThread.setName("thread test");
			myThread.start();
			String globalThreadId = PID.getPID() + '_' + Parameters.getHostAddress() + '_'
					+ myThread.getId();
			assertNotNull("message KILL_THREAD", Action.KILL_THREAD.execute(collector, counterName,
					sessionId, globalThreadId));
			globalThreadId = PID.getPID() + '_' + Parameters.getHostAddress() + '_' + 10000;
			assertNotNull("message KILL_THREAD", Action.KILL_THREAD.execute(collector, counterName,
					sessionId, globalThreadId));
		} finally {
			timer.cancel();
		}
	}