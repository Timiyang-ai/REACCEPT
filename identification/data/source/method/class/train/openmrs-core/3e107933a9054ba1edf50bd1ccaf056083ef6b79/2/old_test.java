	@Test
	public void equalsContent_shouldIndicateUnequalWhenOnlyAddressOneDiffers() {
		PersonAddress rileyStreetAddress = new PersonAddress();
		PersonAddress crownStreetAddress = new PersonAddress();
		crownStreetAddress.setAddress1("crown street");
		rileyStreetAddress.setAddress1("riley street");
		
		assertThat(crownStreetAddress.equalsContent(rileyStreetAddress), is(false));
	}