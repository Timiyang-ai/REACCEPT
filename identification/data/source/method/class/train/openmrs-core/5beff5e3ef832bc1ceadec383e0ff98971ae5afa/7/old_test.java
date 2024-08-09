	@Test
	public void handle_shouldUnsetThePersonVoidedBit() {
		UnvoidHandler<Person> handler = new PersonUnvoidHandler();
		Person person = new Person();
		person.setPersonVoided(true); // make sure personVoided is set
		handler.handle(person, null, null, null);
		Assert.assertFalse(person.getPersonVoided());
	}