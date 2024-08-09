@Test
	public void findAllByExample() {

		flushTestUsers();

		User prototype = new User();
		prototype.setAge(28);
		prototype.setCreatedAt(null);

		List<User> users = repository.findAll(of(prototype));

		assertThat(users, hasSize(1));
		assertThat(users.get(0), is(firstUser));
	}