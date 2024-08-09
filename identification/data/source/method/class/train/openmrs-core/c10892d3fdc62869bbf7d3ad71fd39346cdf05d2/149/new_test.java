	@Test
	public void unretireLocation_shouldUnretireRetiredLocation() {
		LocationService ls = Context.getLocationService();
		
		Location loc = ls.getLocation("Test Retired Location");
		Assert.assertTrue(loc.getRetired());
		
		Location newLoc = ls.unretireLocation(loc);
		Assert.assertEquals(loc, newLoc);
		Assert.assertFalse(loc.getRetired());
		Assert.assertNull(loc.getRetiredBy());
		Assert.assertNull(loc.getRetireReason());
	}