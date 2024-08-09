	@Test
	public void saveLocation_shouldCreateLocationSuccessfully() {
		Location location = new Location();
		
		location.setName("testing");
		location.setDescription("desc");
		location.setAddress1("123");
		location.setAddress1("456");
		location.setCityVillage("city");
		location.setStateProvince("state");
		location.setCountry("country");
		location.setPostalCode("post");
		location.setLatitude("lat");
		location.setLongitude("lon");
		
		LocationService ls = Context.getLocationService();
		ls.saveLocation(location);
		
		Location newSavedLocation = ls.getLocation(location.getLocationId());
		
		assertNotNull("The saved location should have an id now", location.getLocationId());
		assertNotNull("We should get back a location", newSavedLocation);
		assertTrue("The created location needs to equal the pojo location", location.equals(newSavedLocation));
	}