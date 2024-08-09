	@Test
	public void getIdentifiers_shouldNotReturnNull() {
		Patient p = new Patient();
		p.setIdentifiers(null);
		Assert.assertNotNull(p.getIdentifiers());
	}