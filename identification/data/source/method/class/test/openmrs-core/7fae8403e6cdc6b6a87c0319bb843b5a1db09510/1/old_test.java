	@Test
	public void parsePersonName_shouldParseTwoPersonNameWithComma() throws Exception {
		PersonName pname = Context.getPersonService().parsePersonName("Doe, John");
		assertEquals("Doe", pname.getFamilyName());
		assertEquals("John", pname.getGivenName());
		
		// try without a space
		pname = Context.getPersonService().parsePersonName("Doe,John");
		assertEquals("Doe", pname.getFamilyName());
		assertEquals("John", pname.getGivenName());
	}