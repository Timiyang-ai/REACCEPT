	@Test
	public void handle_shouldUnsetTheVoidedBit() {
		UnvoidHandler<Voidable> handler = new BaseUnvoidHandler();
		Voidable voidable = new Person();
		voidable.setVoided(true);
		handler.handle(voidable, null, null, null);
		Assert.assertFalse(voidable.getVoided());
	}