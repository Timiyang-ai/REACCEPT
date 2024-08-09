@Test
	public void testRequestChannel_batches_upstream_echo() throws InterruptedException {
		ControlledSubscriber s = new ControlledSubscriber();
		AtomicInteger emittedClient = new AtomicInteger();
		socketClient.requestChannel(toPublisher(
				range(1, 10000)
				.doOnNext(n -> emittedClient.incrementAndGet())
				.doOnRequest(r -> System.out.println("CLIENT REQUESTS requestN: " + r))
				.map(i -> {
					return utf8EncodedPayload(String.valueOf(i), "echo"); // metadata to route us to the echo behavior (only actually need this in the first payload) 
				}))).subscribe(s);
		
		assertEquals(0, s.received.get());
		assertEquals(0, emitted.get());
		assertEquals(0, emittedClient.get());
		s.subscription.request(10);
		waitForAsyncValue(s.received, 10);
		assertEquals(10, emittedClient.get());
		assertEquals(10, s.received.get());
		s.subscription.request(200);
		waitForAsyncValue(s.received, 210);
		assertEquals(210, emittedClient.get());
		assertEquals(210, s.received.get());
		Thread.sleep(100);
		assertFalse(s.error.get());
		
		System.out.println(">>> Client sent " + emittedClient.get() + " requests and received " + s.received.get() + " responses");
	}