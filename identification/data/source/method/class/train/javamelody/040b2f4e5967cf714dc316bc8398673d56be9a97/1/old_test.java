@Test
	public void testStop() throws IOException {
		try {
			final Collector collector = createCollectorWithOneCounter();
			collector.stop();
			if (collector.getCounters().size() == 0) {
				fail("collector.getCounters() ne doit pas être vide après stop");
			}
			// on ne risque pas grand chose à tenter de détacher quelque chose que l'on a pas attacher
			Collector.detachVirtualMachine();

			// on provoque une erreur, mais elle ne doit pas remonter (seulement trace dans console)
			setProperty(Parameter.STORAGE_DIRECTORY, "/???");
			final Counter counter = createCounter();
			final Collector collector2 = new Collector("test stop",
					Collections.singletonList(counter), timer);
			counter.addRequest("test stop", 0, 0, false, 1000);
			collector2.collectWithoutErrors(Collections.singletonList(new JavaInformations(null,
					true)));
			collector2.stop();
			setProperty(Parameter.STORAGE_DIRECTORY, "javamelody");

			// à défaut de pouvoir appeler JRobin.stop() car les autres tests ne pourront plus
			// utiliser JRobin, on appelle au moins JRobin.getJRobinFileSyncTimer()
			JRobin.getJRobinFileSyncTimer();
		} finally {
			timer.cancel();
		}
	}