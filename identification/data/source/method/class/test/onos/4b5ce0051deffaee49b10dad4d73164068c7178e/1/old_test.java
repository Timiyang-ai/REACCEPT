@Test(expected = Exception.class)
    public void testUpdateInterfaceMap() throws Exception {
        isisChannelHandler.updateInterfaceMap(isisProcessList);
        assertThat(isisChannelHandler, is(notNullValue()));
    }