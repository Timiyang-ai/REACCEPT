@Test
    public void testbuildCombinedFragment() {
        byte[] original = ArrayConverter.hexStringToByteArray("123456789A123456789A");
        collector.addFragment(fragmentOfMsg(0, 0, 3, original));
        collector.addFragment(fragmentOfMsg(0, 3, 5, original));
        collector.addFragment(fragmentOfMsg(0, 8, 2, original));
        DtlsHandshakeMessageFragment fragment = collector.buildCombinedFragment();
        checkFragment(fragment, 0, 10, original);
    }