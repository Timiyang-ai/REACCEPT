	@Test
	public void handle_shouldSetThePersonVoidedBit() {
		VoidHandler<Person> handler = new PersonVoidHandler();
		Person person = new Person();
		person.setPersonVoided(false); // make sure personVoided is false
		handler.handle(person, null, null, " ");
		Assert.assertTrue(person.getPersonVoided());
	}