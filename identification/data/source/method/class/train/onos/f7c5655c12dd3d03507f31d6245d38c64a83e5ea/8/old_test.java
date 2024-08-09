@Test
    public void testDeleteLink() throws Exception {
        ospfRouter = new OspfRouterImpl();

        ospfController.agent.addLink(ospfRouter, new OspfLinkTedImpl());
        ospfController.agent.deleteLink(ospfRouter);
        assertThat(ospfController, is(notNullValue()));
    }