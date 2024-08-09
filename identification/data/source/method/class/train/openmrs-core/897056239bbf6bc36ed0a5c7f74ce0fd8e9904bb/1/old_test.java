	@Test
	public void equalsContent_shouldReturnTrueIfAllFieldsOtherThanIdPersonAndPreferredAreEqual() {
		PersonName pn = new PersonName(1);
		pn.setPrefix("Count");
		pn.setGivenName("Adam");
		pn.setMiddleName("Alex");
		pn.setFamilyNamePrefix("family prefix");
		pn.setFamilyName("Jones");
		pn.setFamilyName2("Howard");
		pn.setFamilyNameSuffix("Jr.");
		pn.setDegree("Dr.");
		pn.setPreferred(true);
		pn.setPerson(new Person(999));
		
		PersonName other = new PersonName(2);
		other.setPrefix("Count");
		other.setGivenName("Adam");
		other.setMiddleName("Alex");
		other.setFamilyNamePrefix("family prefix");
		other.setFamilyName("Jones");
		other.setFamilyName2("Howard");
		other.setFamilyNameSuffix("Jr.");
		other.setDegree("Dr.");
		other.setPreferred(false);
		other.setPerson(new Person(111));
		
		assertThat(pn.equalsContent(other), is(true));
	}