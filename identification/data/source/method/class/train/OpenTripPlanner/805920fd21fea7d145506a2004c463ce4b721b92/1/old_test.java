@Test
    public void testGenerateItinerary() {
        GraphPath[] graphPaths = buildPaths();

        compare(GraphPathToTripPlanConverter.generateItinerary(graphPaths[0], true, locale), Type.FORWARD);
        compare(GraphPathToTripPlanConverter.generateItinerary(graphPaths[1], true, locale), Type.BACKWARD);
        compare(GraphPathToTripPlanConverter.generateItinerary(graphPaths[2], true, locale), Type.ONBOARD);
    }