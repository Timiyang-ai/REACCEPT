	@Test
	public void handle_shouldUnsetTheRetiredBit() {
		UnretireHandler<Retireable> handler = new BaseUnretireHandler();
		Retireable retireable = new Location();
		retireable.setRetired(true); // make sure isRetired is set
		handler.handle(retireable, null, null, null);
		Assert.assertFalse(retireable.getRetired());
	}