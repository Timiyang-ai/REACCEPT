@Test
	public void deleteAll() throws Exception {

		flushTestUsers();

		repository.deleteAll();

		assertThat(repository.count(), is(0L));
	}