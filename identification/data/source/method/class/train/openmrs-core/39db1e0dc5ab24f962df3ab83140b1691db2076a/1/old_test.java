	@Test
	public void getDescendantLocations_shouldReturnAllDescendantLocationsIfIncludeRetiredIsTrue() {
		
		Location rootLocation = new Location();
		//first level
		Location locationOne = new Location();
		Location locationTwo = new Location();
		//second level
		Location childOflocationOne = new Location();
		Location childOnfLocationTwo = new Location();
		
		//make child-parent relations
		rootLocation.setChildLocations(new HashSet<>(Arrays.asList(locationOne, locationTwo)));
		
		locationOne.setChildLocations(new HashSet<>(Collections.singletonList(childOflocationOne)));
		locationTwo.setChildLocations(new HashSet<>(Collections.singletonList(childOnfLocationTwo)));
		
		childOflocationOne.setChildLocations(new HashSet<>());
		childOnfLocationTwo.setChildLocations(new HashSet<>());
		
		Set<Location> descendantLocations = rootLocation.getDescendantLocations(true);
		
		Set<Location> expectedLocations = new HashSet<>(Arrays.asList(locationOne, locationTwo, childOflocationOne,
		    childOnfLocationTwo));
		Assert.assertThat(descendantLocations, equalTo(expectedLocations));
		
	}