@Test
    public void testCompressStringifiedRules() throws Exception {
        String compressed = "eJzztEpMSrYytDKyMtQz0jPWM9E31THTM9ez0LPUN9Dxc40IUXAlrAQAPdoP3Q==";
        String a = securityGroupRulesCmd.compressStringifiedRules();
        assertTrue(compressed.equals(a));
    }