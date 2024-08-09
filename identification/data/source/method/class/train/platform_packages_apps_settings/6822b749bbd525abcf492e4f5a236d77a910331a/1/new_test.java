    @Test
    public void getWifiIpAddresses_succeeds() throws Exception {
        when(wifiManager.getCurrentNetwork()).thenReturn(network);
        LinkAddress address = new LinkAddress(InetAddress.getByName("127.0.0.1"), 0);
        LinkProperties lp = new LinkProperties();
        lp.addLinkAddress(address);
        when(connectivityManager.getLinkProperties(network)).thenReturn(lp);

        assertThat(Utils.getWifiIpAddresses(mContext)).isEqualTo("127.0.0.1");
    }