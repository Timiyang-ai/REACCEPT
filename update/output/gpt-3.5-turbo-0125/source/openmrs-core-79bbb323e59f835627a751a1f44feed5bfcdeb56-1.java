@Test
	public void parsePersonName_shouldNotFailWhenEndingWithWhitespace() throws Exception {
		PersonName personName = Context.getPersonService().parsePersonName("John ");
		assertEquals("John", personName.getGivenName());
		assertEquals("", personName.getMiddleName());
		assertEquals("", personName.getFamilyName());
		assertNull(personName.getFamilyName2());
	}