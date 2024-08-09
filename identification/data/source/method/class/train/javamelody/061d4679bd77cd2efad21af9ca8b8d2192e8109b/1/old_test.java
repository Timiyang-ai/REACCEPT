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

			assertNotNull("message GC", Action.GC.execute(collector, counterName, sessionId));
			assertNotNull("message CLEAR_COUNTER", Action.CLEAR_COUNTER.execute(collector,
					counterName, sessionId));
			if (CacheManager.getInstance().getCache("test clear") == null) {
				CacheManager.getInstance().addCache("test clear");
			}
			assertNotNull("message CLEAR_CACHES", Action.CLEAR_CACHES.execute(collector,
					counterName, sessionId));
			final String heapDump1 = Action.HEAP_DUMP.execute(collector, counterName, sessionId);
			assertNotNull("message HEAP_DUMP", heapDump1);
			String heapDump2;
			// on le refait une deuxième fois dans la même seconde pour tester le nom du fichier
			do {
				heapDump2 = Action.HEAP_DUMP.execute(collector, counterName, sessionId);
				assertNotNull("message HEAP_DUMP", heapDump2);
			} while (heapDump1.equals(heapDump2));
			for (final File file : Parameters.TEMPORARY_DIRECTORY.listFiles()) {
				if (!file.isDirectory() && file.getName().startsWith("heapdump") && !file.delete()) {
					file.deleteOnExit();
				}
			}
			assertNotNull("message INVALIDATE_SESSIONS", Action.INVALIDATE_SESSIONS.execute(
					collector, counterName, sessionId));
			assertNotNull("message INVALIDATE_SESSION", Action.INVALIDATE_SESSION.execute(
					collector, counterName, sessionId));
		} finally {
			timer.cancel();
		}
	}