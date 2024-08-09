@Test
	@Verifies(value = "should return true if given middle and family name are equal", method = "equalsContent(PersonName)")
	public void equalsContent_shouldReturnTrueIfGivenMiddleAndFamilyNameAreEqual() throws Exception {
		PersonName pn = new PersonName(1); // a different person name id than below
		pn.setGivenName("Adam");
		pn.setMiddleName("Alex");
		pn.setFamilyName("Jones");
		PersonName other = new PersonName(2); // a different person name id than above
		other.setGivenName("Adam");
		other.setMiddleName("Alex");
		other.setFamilyName("Jones");
		
		Assert.assertTrue(pn.equalsContent(other));
	}