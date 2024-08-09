@Test
	@Verifies(value = "should return negative if other attribute is voided", method = "compareTo(PersonAttribute)")
	public void compareTo_shouldReturnNegativeIfOtherAttributeIsVoided() throws Exception {
		PersonAttribute pa = new PersonAttribute();
		PersonAttribute other = new PersonAttribute();
		other.setVoided(true);
		Assert.assertTrue(pa.compareTo(other) < 0);
	}