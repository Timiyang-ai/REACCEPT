    public void test_isLiteralIpAddress_IPv4_Success() throws Exception {
        assertTrue(AddressUtils.isLiteralIpAddress("127.0.0.1"));
        assertTrue(AddressUtils.isLiteralIpAddress("255.255.255.255"));
        assertTrue(AddressUtils.isLiteralIpAddress("0.0.00.000"));
        assertTrue(AddressUtils.isLiteralIpAddress("192.009.010.19"));
        assertTrue(AddressUtils.isLiteralIpAddress("254.249.190.094"));
    }