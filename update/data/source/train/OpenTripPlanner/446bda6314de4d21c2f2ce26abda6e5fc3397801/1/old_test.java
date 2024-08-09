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

        RoutingRequest rr = (new RoutingRequestBuilder()).addTripParameters(tp).build();

        GenericLocation from = rr.getFrom();
        Coordinate expectedFromCoord = new Coordinate(2.5, 1.0);
        assertEquals(expectedFromCoord, from.getCoordinate());

        GenericLocation to = rr.getTo();
        Coordinate expectedToCoord = new Coordinate(9.7, -3.0);
        assertEquals(expectedToCoord, to.getCoordinate());

        for (TravelMode tm : tp.getAllowed_modes()) {
            TraverseModeSet modeSet = rr.getModes();
            TraverseMode traverseMode = (new TravelModeWrapper(tm)).toTraverseMode();
            assertTrue(modeSet.contains(traverseMode));
        }
    }