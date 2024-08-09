	@Test
	public void getAge_shouldGetAgeAfterBirthday() {
		Calendar birthdate = Calendar.getInstance();
		birthdate.set(2006, Calendar.JUNE, 2);
		Calendar onDate = Calendar.getInstance();
		onDate.set(2008, Calendar.JUNE, 3);
		Person person = new Person();
		person.setBirthdate(birthdate.getTime());
		assertEquals(person.getAge(onDate.getTime()), 2, 0);
	}