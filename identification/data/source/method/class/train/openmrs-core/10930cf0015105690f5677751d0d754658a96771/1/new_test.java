	@Test
	public void getFullName_shouldNotPutSpacesAroundAnEmptyMiddleName() {
		PersonName pn = new PersonName();
		pn.setGivenName("Bob");
		pn.setMiddleName("");
		pn.setFamilyName("Jones");
		Assert.assertEquals("Bob Jones", pn.getFullName());
	}