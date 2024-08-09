@Test
	@Verifies(value = "should unset the personVoided bit", method = "handle(Voidable,User,Date,String)")
	public void handle_shouldUnsetThePersonVoidedBit() throws Exception {
		UnvoidHandler<Person> handler = new PersonUnvoidHandler();
		Person person = new Person();
		person.setPersonVoided(true); // make sure isPersonVoided is set
		handler.handle(person, null, null, null);
		Assert.assertFalse(person.isPersonVoided());
	}