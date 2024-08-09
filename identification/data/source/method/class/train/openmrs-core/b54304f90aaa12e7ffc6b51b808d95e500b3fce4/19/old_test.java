	@Test
	public void equalsContent_shouldReturnTrueIfAttributeTypeValueAndVoidStatusAreTheSame() {
		PersonAttribute pa = new PersonAttribute(2); // a different personAttributeid than below
		pa.setAttributeType(new PersonAttributeType(1));
		pa.setValue("1");
		pa.setVoided(false);
		PersonAttribute other = new PersonAttribute(1); // a different personAttributeid than above
		pa.setAttributeType(new PersonAttributeType(1));
		pa.setValue("1");
		pa.setVoided(false);
		
		Assert.assertTrue(pa.equalsContent(other));
	}