	@Test
	public void handle_shouldSetTheVoidedBit() {
		VoidHandler<Voidable> handler = new BaseVoidHandler();
		Voidable voidable = new Person();
		voidable.setVoided(false);
		handler.handle(voidable, null, null, " ");
		Assert.assertTrue(voidable.getVoided());
	}