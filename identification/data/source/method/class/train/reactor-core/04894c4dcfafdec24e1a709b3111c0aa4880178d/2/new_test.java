	@Test
	public void bubble() throws Exception {

		Throwable t = new Exception("test");

		Throwable w = Exceptions.bubble(Exceptions.propagate(t));

		assertTrue(Exceptions.unwrap(w) == t);
	}