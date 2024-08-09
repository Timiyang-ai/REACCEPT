@Test
	@Verifies(value = "should return false if family name 2 is not equal", method = "equalsContent(PersonName)")
	public void equalsContent_shouldReturnFalseIfFamilyName2IsNotEqual() throws Exception {
		PersonName name1 = new PersonName(1);
		PersonName name2 = new PersonName(2);
		
		name1.setFamilyName2("van der");
		name2.setFamilyName2("de");
		
		assertThat(name1.equalsContent(name2), is(false));
	}