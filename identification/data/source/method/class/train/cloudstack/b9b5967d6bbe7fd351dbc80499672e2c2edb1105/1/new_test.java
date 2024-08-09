@Test
    public void testCompressStringifiedRules() throws Exception {
        final String compressed = "eJzztEpMSrYytDKyMtQz0jPWM9E31THTM9ez0LPUN9Dxc40IUXAlrAQAPdoP3Q==";
        final String a = securityGroupRulesCmd.compressStringifiedRules();
        assertTrue(compressed.equals(a));
    }