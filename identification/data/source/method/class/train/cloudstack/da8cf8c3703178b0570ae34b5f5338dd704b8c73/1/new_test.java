    @Test
    public void getRecommendedProtocolsTest() {
        ArrayList<String> protocolsList = new ArrayList<>(Arrays.asList(spySSLUtils.getRecommendedProtocols()));
        verifyProtocols(protocolsList);
    }