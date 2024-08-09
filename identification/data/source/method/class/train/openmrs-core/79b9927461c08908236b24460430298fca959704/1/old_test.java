	@Test
	public void isInHierarchy_shouldShouldFindLocationInHierarchy() {
		Location locationGrandParent = new Location();
		Location locationParent = new Location();
		Location locationChild = new Location();
		
		locationGrandParent.addChildLocation(locationParent);
		locationParent.addChildLocation(locationChild);
		
		assertTrue(Location.isInHierarchy(locationChild, locationParent));
		assertTrue(Location.isInHierarchy(locationChild, locationGrandParent));
	}