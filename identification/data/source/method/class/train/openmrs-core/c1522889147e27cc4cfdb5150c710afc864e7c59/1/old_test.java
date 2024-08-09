	@Test
	public void voidAttribute_shouldSetVoidedBitToTrue() {
		PersonAttribute pa = new PersonAttribute(2);
		pa.setVoided(false);
		pa.voidAttribute("Because");
		Assert.assertTrue(pa.getVoided());
	}