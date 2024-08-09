@Test
	public void parsePersonName_shouldNotFailWhenEndingWithWhitespace() throws Exception {
		Context.getPersonService().parsePersonName("John ");
	}