@Test public void testNetworkInterfaces() throws Exception {
        new TestNetworkInterface().runTest(azure2.networkInterfaces(), azure2.resourceGroups());
    }