@Test
    public void testGenerateItinerary() {
        GraphPath[] graphPaths = buildPaths();

        compare(GraphPathToTripPlanConverter.generateItinerary(graphPaths[0], true, false, locale), Type.FORWARD);
        compare(GraphPathToTripPlanConverter.generateItinerary(graphPaths[1], true, false, locale), Type.BACKWARD);
        compare(GraphPathToTripPlanConverter.generateItinerary(graphPaths[2], true, false, locale), Type.ONBOARD);
    }