	@Test
	public void handle_shouldSetTheRetiredBit() {
		RetireHandler<Retireable> handler = new BaseRetireHandler();
		Retireable retireable = new Location();
		retireable.setRetired(false); // make sure isRetired is false
		handler.handle(retireable, null, null, " ");
		Assert.assertTrue(retireable.getRetired());
	}