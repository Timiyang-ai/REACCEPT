@Test
	public void testAddTripParameters() {
		TripParameters tp = new TripParameters();
		tp.addToAllowed_modes(TravelMode.WALK);
		tp.addToAllowed_modes(TravelMode.CAR);

		LatLng originLatLng = new LatLng(1.0, 2.5);
		Location origin = new Location();
		origin.setLat_lng(originLatLng);

		LatLng destLatLng = new LatLng(-3.0, 9.7);
		Location dest = new Location();
		dest.setLat_lng(destLatLng);

		tp.setOrigin(origin);
		tp.setDestination(dest);

		RoutingRequest rr = (new RoutingRequestBuilder()).addTripParameters(tp)
				.build();

		assertEquals("1.0000000,2.5000000", rr.getFrom());
		assertEquals("-3.0000000,9.7000000", rr.getTo());

		for (TravelMode tm : tp.getAllowed_modes()) {
			TraverseModeSet modeSet = rr.getModes();
			TraverseMode traverseMode = (new TravelModeWrapper(tm))
					.toTraverseMode();
			assertTrue(modeSet.contains(traverseMode));
		}
	}