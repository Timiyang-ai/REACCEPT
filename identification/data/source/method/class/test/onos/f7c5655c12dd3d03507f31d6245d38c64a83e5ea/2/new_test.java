@Test
    public void testElectDR() throws Exception {
        ospfEligibleRouter = new OspfEligibleRouter();
        ospfEligibleRouter.setIpAddress(Ip4Address.valueOf("1.1.1.1"));
        ospfEligibleRouter.setIsDr(true);
        ospfEligibleRouter.setRouterPriority(10);
        ospfEligibleRouter.setRouterId(Ip4Address.valueOf("1.1.1.1"));
        ospfEligibleRouter.setIsBdr(false);
        OspfEligibleRouter ospfEligibleRouter1 = new OspfEligibleRouter();
        ospfEligibleRouter.setIpAddress(Ip4Address.valueOf("1.1.1.1"));
        ospfEligibleRouter.setIsDr(true);
        ospfEligibleRouter.setRouterPriority(10);
        ospfEligibleRouter.setRouterId(Ip4Address.valueOf("1.1.1.1"));
        ospfEligibleRouter.setIsBdr(false);
        OspfEligibleRouter ospfEligibleRouter2 = new OspfEligibleRouter();
        ospfEligibleRouter.setIpAddress(Ip4Address.valueOf("1.1.1.1"));
        ospfEligibleRouter.setIsDr(true);
        ospfEligibleRouter.setRouterPriority(10);
        ospfEligibleRouter.setRouterId(Ip4Address.valueOf("1.1.1.1"));
        ospfEligibleRouter.setIsBdr(false);
        List<OspfEligibleRouter> ospfEligibleRouters = new ArrayList<>();
        ospfEligibleRouters.add(ospfEligibleRouter);
        ospfEligibleRouters.add(ospfEligibleRouter1);
        ospfEligibleRouters.add(ospfEligibleRouter2);
        OspfEligibleRouter eligibleRouter = ospfInterface.electDr(ospfEligibleRouters,
                                                                  ospfEligibleRouter);
        assertThat(ospfEligibleRouters.size(), is(3));
        assertThat(eligibleRouter, is(notNullValue()));
    }