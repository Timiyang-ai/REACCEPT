@Test
	@Verifies(value = "should unset the retired bit", method = "handle(Retireable,User,Date,String)")
	public void handle_shouldUnsetTheRetiredBit() throws Exception {
		UnretireHandler<Retireable> handler = new BaseUnretireHandler();
		Retireable retireable = new Location();
		retireable.setRetired(true); // make sure isRetired is set
		handler.handle(retireable, null, null, null);
		Assert.assertFalse(retireable.getRetired());
	}