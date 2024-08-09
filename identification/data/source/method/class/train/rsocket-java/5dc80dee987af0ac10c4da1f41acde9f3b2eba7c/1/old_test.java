@Ignore // TODO
	@Test
	public void testRequestChannel_batches_upstream() {
		ControlledSubscriber s = new ControlledSubscriber();
		socketClient.requestChannel(toPublisher(
				range(1, 10000)
				.map(i -> {
					return utf8EncodedPayload(String.valueOf(i), "echo"); // metadata to route us to the echo behavior (only actually need this in the first payload) 
				}))).subscribe(s);
		
		assertEquals(0, s.received);
		assertEquals(0, emitted.get());
		s.subscription.request(10);
		assertFalse(s.error.get());
	}