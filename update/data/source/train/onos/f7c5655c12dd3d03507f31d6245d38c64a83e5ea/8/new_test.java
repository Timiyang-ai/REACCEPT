@Test
    public void testRemoveLinkDetails() throws Exception {
        ospfAgent = EasyMock.createMock(OspfAgent.class);
        controller.start(ospfAgent, driverService);
        ospfRouter = new OspfRouterImpl();
        controller.addLinkDetails(ospfRouter, new OspfLinkTedImpl());
        controller.removeLinkDetails(ospfRouter, new OspfLinkTedImpl());
        assertThat(controller, is(notNullValue()));
    }