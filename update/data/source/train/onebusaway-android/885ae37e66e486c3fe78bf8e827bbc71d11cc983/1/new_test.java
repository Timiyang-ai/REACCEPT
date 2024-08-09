@Test
    public void testBuildTripOptions() {
        // Initial setup to get an ObaArrivalInfo object from a test response
        ObaRegion tampa = MockRegion.getTampa(getTargetContext());
        assertNotNull(tampa);
        Application.get().setCurrentRegion(tampa);

        ObaArrivalInfoResponse response =
                new ObaArrivalInfoRequest.Builder(getTargetContext(),
                        "Hillsborough Area Regional Transit_3105").build().call();
        assertOK(response);
        ObaStop stop = response.getStop();
        assertNotNull(stop);
        assertEquals("Hillsborough Area Regional Transit_3105", stop.getId());
        List<ObaRoute> routes = response.getRoutes(stop.getRouteIds());
        assertTrue(routes.size() > 0);
        ObaAgency agency = response.getAgency(routes.get(0).getAgencyId());
        assertEquals("Hillsborough Area Regional Transit", agency.getId());

        ObaArrivalInfo[] arrivals = response.getArrivalInfo();
        assertNotNull(arrivals);
        ArrayList<ArrivalInfo> arrivalInfo = ArrivalInfoUtils.convertObaArrivalInfo(getTargetContext(),
                arrivals, null, response.getCurrentTime(), true);

        ObaRoute route = response.getRoute(arrivalInfo.get(0).getInfo().getRouteId());
        String url = route != null ? route.getUrl() : null;
        boolean hasUrl = !TextUtils.isEmpty(url);
        boolean isReminderVisible = false;  // We don't have views here, so just fake it
        boolean isRouteFavorite = false;  // We'll fake this too, for our purposes

        Occupancy occupancy = null;
        OccupancyState occupancyState = null;
        if (arrivalInfo.get(0).getInfo().getPredictedOccupancy() != null) {
            occupancy = arrivalInfo.get(0).getInfo().getPredictedOccupancy();
            occupancyState = OccupancyState.PREDICTED;
        } else if (arrivalInfo.get(0).getInfo().getHistoricalOccupancy() != null) {
            occupancy = arrivalInfo.get(0).getInfo().getHistoricalOccupancy();
            occupancyState = OccupancyState.HISTORICAL;
        }

        // HART has route schedule URLs in test data, so below options should allow the user to set
        // a reminder and view the route schedule
        List<String> options = UIUtils
                .buildTripOptions(getTargetContext(), isRouteFavorite, hasUrl, isReminderVisible, occupancy, occupancyState);
        assertEquals(8, options.size());
        assertEquals(options.get(0), "Add star to route");
        assertEquals(options.get(1), "Show route on map");
        assertEquals(options.get(2), "Show trip status");
        assertEquals(options.get(3), "Set a reminder");
        assertEquals(options.get(4), "Show only this route");
        assertEquals(options.get(5), "Show route schedule");
        assertEquals(options.get(6), "Report arrival time problem");
        assertEquals(options.get(7), "Join discussion");

        isReminderVisible = true;

        // Now we should see route schedules and *edit* the reminder
        options = UIUtils
                .buildTripOptions(getTargetContext(), isRouteFavorite, hasUrl, isReminderVisible, occupancy, occupancyState);
        assertEquals(8, options.size());
        assertEquals(options.get(0), "Add star to route");
        assertEquals(options.get(1), "Show route on map");
        assertEquals(options.get(2), "Show trip status");
        assertEquals(options.get(3), "Edit this reminder");
        assertEquals(options.get(4), "Show only this route");
        assertEquals(options.get(5), "Show route schedule");
        assertEquals(options.get(6), "Report arrival time problem");
        assertEquals(options.get(7), "Join discussion");

        // Get a PSTA response - PSTA test data doesn't include route schedule URLs
        ObaArrivalInfoResponse response2 =
                new ObaArrivalInfoRequest.Builder(getTargetContext(), "PSTA_4077").build().call();
        assertOK(response2);

        stop = response2.getStop();
        assertNotNull(stop);
        assertEquals("PSTA_4077", stop.getId());
        routes = response2.getRoutes(stop.getRouteIds());
        assertTrue(routes.size() > 0);
        agency = response2.getAgency(routes.get(0).getAgencyId());
        assertEquals("PSTA", agency.getId());

        arrivals = response2.getArrivalInfo();
        assertNotNull(arrivals);
        arrivalInfo = ArrivalInfoUtils.convertObaArrivalInfo(getTargetContext(),
                arrivals, null, response2.getCurrentTime(), true);

        route = response2.getRoute(arrivalInfo.get(0).getInfo().getRouteId());
        url = route != null ? route.getUrl() : null;
        boolean hasUrl2 = !TextUtils.isEmpty(url);
        isReminderVisible = false;  // We don't have views here, so just fake it

        // PSTA does not have route schedule URLs in test data, so below options should allow the
        // user to set a reminder but NOT view the route schedule
        options = UIUtils
                .buildTripOptions(getTargetContext(), isRouteFavorite, hasUrl2, isReminderVisible, occupancy, occupancyState);
        assertEquals(7, options.size());
        assertEquals(options.get(0), "Add star to route");
        assertEquals(options.get(1), "Show route on map");
        assertEquals(options.get(2), "Show trip status");
        assertEquals(options.get(3), "Set a reminder");
        assertEquals(options.get(4), "Show only this route");
        assertEquals(options.get(5), "Report arrival time problem");
        assertEquals(options.get(6), "Join discussion");

        isReminderVisible = true;

        // Now we should see *edit* the reminder, and still no route schedule
        options = UIUtils
                .buildTripOptions(getTargetContext(), isRouteFavorite, hasUrl2, isReminderVisible, occupancy, occupancyState);
        assertEquals(7, options.size());
        assertEquals(options.get(0), "Add star to route");
        assertEquals(options.get(1), "Show route on map");
        assertEquals(options.get(2), "Show trip status");
        assertEquals(options.get(3), "Edit this reminder");
        assertEquals(options.get(4), "Show only this route");
        assertEquals(options.get(5), "Report arrival time problem");
        assertEquals(options.get(6), "Join discussion");

        // Now change route to favorite, and do all the above over again
        isRouteFavorite = true;

        // HART
        isReminderVisible = false;  // We don't have views here, so just fake it

        // HART has route schedule URLs in test data, so below options should allow the user to set
        // a reminder and view the route schedule
        options = UIUtils
                .buildTripOptions(getTargetContext(), isRouteFavorite, hasUrl, isReminderVisible, occupancy, occupancyState);
        assertEquals(8, options.size());
        assertEquals(options.get(0), "Remove star from route");
        assertEquals(options.get(1), "Show route on map");
        assertEquals(options.get(2), "Show trip status");
        assertEquals(options.get(3), "Set a reminder");
        assertEquals(options.get(4), "Show only this route");
        assertEquals(options.get(5), "Show route schedule");
        assertEquals(options.get(6), "Report arrival time problem");
        assertEquals(options.get(7), "Join discussion");

        isReminderVisible = true;

        // Now we should see route schedules and *edit* the reminder
        options = UIUtils
                .buildTripOptions(getTargetContext(), isRouteFavorite, hasUrl, isReminderVisible, occupancy, occupancyState);
        assertEquals(8, options.size());
        assertEquals(options.get(0), "Remove star from route");
        assertEquals(options.get(1), "Show route on map");
        assertEquals(options.get(2), "Show trip status");
        assertEquals(options.get(3), "Edit this reminder");
        assertEquals(options.get(4), "Show only this route");
        assertEquals(options.get(5), "Show route schedule");
        assertEquals(options.get(6), "Report arrival time problem");
        assertEquals(options.get(7), "Join discussion");

        // PSTA
        isReminderVisible = false;  // We don't have views here, so just fake it

        // PSTA does not have route schedule URLs in test data, so below options should allow the
        // user to set a reminder but NOT view the route schedule
        options = UIUtils
                .buildTripOptions(getTargetContext(), isRouteFavorite, hasUrl2, isReminderVisible, occupancy, occupancyState);
        assertEquals(7, options.size());
        assertEquals(options.get(0), "Remove star from route");
        assertEquals(options.get(1), "Show route on map");
        assertEquals(options.get(2), "Show trip status");
        assertEquals(options.get(3), "Set a reminder");
        assertEquals(options.get(4), "Show only this route");
        assertEquals(options.get(5), "Report arrival time problem");
        assertEquals(options.get(6), "Join discussion");

        isReminderVisible = true;

        // Now we should see *edit* the reminder, and still no route schedule
        options = UIUtils
                .buildTripOptions(getTargetContext(), isRouteFavorite, hasUrl2, isReminderVisible, occupancy, occupancyState);
        assertEquals(7, options.size());
        assertEquals(options.get(0), "Remove star from route");
        assertEquals(options.get(1), "Show route on map");
        assertEquals(options.get(2), "Show trip status");
        assertEquals(options.get(3), "Edit this reminder");
        assertEquals(options.get(4), "Show only this route");
        assertEquals(options.get(5), "Report arrival time problem");
        assertEquals(options.get(6), "Join discussion");

        //
        // Test occupancy in the menu
        //

        // HISTORICAL
        occupancy = Occupancy.EMPTY;
        occupancyState = OccupancyState.HISTORICAL;
        options = UIUtils
                .buildTripOptions(getTargetContext(), isRouteFavorite, hasUrl2, isReminderVisible, occupancy, occupancyState);
        assertEquals(8, options.size());
        assertEquals(options.get(0), "Remove star from route");
        assertEquals(options.get(1), "Show route on map");
        assertEquals(options.get(2), "Show trip status");
        assertEquals(options.get(3), "Edit this reminder");
        assertEquals(options.get(4), "Show only this route");
        assertEquals(options.get(5), "Report arrival time problem");
        assertEquals(options.get(6), "Join discussion");
        assertEquals(options.get(7), "About historical occupancy");

        // PREDICTED
        occupancy = Occupancy.EMPTY;
        occupancyState = OccupancyState.PREDICTED;
        options = UIUtils
                .buildTripOptions(getTargetContext(), isRouteFavorite, hasUrl2, isReminderVisible, occupancy, occupancyState);
        assertEquals(8, options.size());
        assertEquals(options.get(0), "Remove star from route");
        assertEquals(options.get(1), "Show route on map");
        assertEquals(options.get(2), "Show trip status");
        assertEquals(options.get(3), "Edit this reminder");
        assertEquals(options.get(4), "Show only this route");
        assertEquals(options.get(5), "Report arrival time problem");
        assertEquals(options.get(6), "Join discussion");
        assertEquals(options.get(7), "About occupancy");

        // REALTIME (should be same as PREDICTED)
        occupancy = Occupancy.EMPTY;
        occupancyState = OccupancyState.REALTIME;
        options = UIUtils
                .buildTripOptions(getTargetContext(), isRouteFavorite, hasUrl2, isReminderVisible, occupancy, occupancyState);
        assertEquals(8, options.size());
        assertEquals(options.get(0), "Remove star from route");
        assertEquals(options.get(1), "Show route on map");
        assertEquals(options.get(2), "Show trip status");
        assertEquals(options.get(3), "Edit this reminder");
        assertEquals(options.get(4), "Show only this route");
        assertEquals(options.get(5), "Report arrival time problem");
        assertEquals(options.get(6), "Join discussion");
        assertEquals(options.get(7), "About occupancy");
    }