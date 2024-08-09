    public void testsubscribeToChildChannelByOSProduct() throws Exception {
        UserTestUtils.addVirtualization(user.getOrg());
        Server s = ServerTestUtils.createTestSystem(user);
        ChannelTestUtils.setupBaseChannelForVirtualization(s.getCreator(),
                s.getBaseChannel());

        assertNotNull(ChannelManager.subscribeToChildChannelByOSProduct(user,
                s, ChannelManager.VT_OS_PRODUCT));

    }