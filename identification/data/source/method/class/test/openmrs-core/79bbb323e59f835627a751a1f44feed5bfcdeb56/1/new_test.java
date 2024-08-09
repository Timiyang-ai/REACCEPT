@Test
	public void parsePersonName_shouldNotFailWhenEndingWithWhitespace() throws Exception {
		PersonName pname = Context.getPersonService().parsePersonName("John ");
		assertEquals("John", pname.getGivenName());
	}