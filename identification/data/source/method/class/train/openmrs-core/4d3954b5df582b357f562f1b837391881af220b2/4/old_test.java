	@Test
	public void savePerson_shouldSavePersonWithBirthDateTime() throws ParseException {
		Person person = new Person();
		person.setBirthtime(new SimpleDateFormat("HH:mm:ss").parse("15:23:56"));
		person.setBirthdate(new SimpleDateFormat("yyyy-MM-dd").parse("2012-05-29"));
		person.setDead(false);
		person.setVoided(false);
		person.setBirthdateEstimated(false);
		person.setId(345);
		hibernatePersonDAO.savePerson(person);

		Person savedPerson = hibernatePersonDAO.getPerson(345);
		Assert.assertEquals(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2012-05-29 15:23:56"), savedPerson.getBirthDateTime());
	}