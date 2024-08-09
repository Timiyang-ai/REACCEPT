@Test
	public void deleteAll() throws Exception {

		flushTestUsers();

		repository.deleteAll();

		assertEquals(0L, repository.count());
	}