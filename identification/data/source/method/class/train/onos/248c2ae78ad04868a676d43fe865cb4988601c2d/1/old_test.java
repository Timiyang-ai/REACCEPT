@Test
    public void testProcessRouteAdd() throws TestUtilsException {
        // Construct a route entry
        RouteEntry routeEntry = new RouteEntry(
                Ip4Prefix.valueOf("1.1.1.0/24"),
                Ip4Address.valueOf("192.168.10.1"));

        // Construct a MultiPointToSinglePointIntent intent
        TrafficSelector.Builder selectorBuilder =
                DefaultTrafficSelector.builder();
        selectorBuilder.matchEthType(Ethernet.TYPE_IPV4).matchIPDst(
                routeEntry.prefix());

        TrafficTreatment.Builder treatmentBuilder =
                DefaultTrafficTreatment.builder();
        treatmentBuilder.setEthDst(MacAddress.valueOf("00:00:00:00:00:01"));

        Set<ConnectPoint> ingressPoints = new HashSet<ConnectPoint>();
        ingressPoints.add(SW2_ETH1);
        ingressPoints.add(SW3_ETH1);

        MultiPointToSinglePointIntent intent =
                new MultiPointToSinglePointIntent(APPID,
                        selectorBuilder.build(), treatmentBuilder.build(),
                        ingressPoints, SW1_ETH1);

        // Set up test expectation
        reset(intentService);
        intentService.submit(TestIntentServiceHelper.eqExceptId(intent));
        replay(intentService);

        // Call the processRouteAdd() method in Router class
        intentSynchronizer.leaderChanged(true);
        TestUtils.setField(intentSynchronizer, "isActivatedLeader", true);
        router.processRouteAdd(routeEntry);

        // Verify
        assertEquals(router.getRoutes().size(), 1);
        assertTrue(router.getRoutes().contains(routeEntry));
        assertEquals(intentSynchronizer.getRouteIntents().size(), 1);
        Intent firstIntent =
            intentSynchronizer.getRouteIntents().iterator().next();
        IntentKey firstIntentKey = new IntentKey(firstIntent);
        IntentKey intentKey = new IntentKey(intent);
        assertTrue(firstIntentKey.equals(intentKey));
        verify(intentService);
    }