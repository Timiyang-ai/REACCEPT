    public void test_getHostAddress() throws Exception {
        assertEquals("::1", localhost6().getHostAddress());
        assertEquals("::1", InetAddress.getByName("::1").getHostAddress());

        assertEquals("127.0.0.1", Inet4Address.LOOPBACK.getHostAddress());

        // IPv4 mapped address
        assertEquals("127.0.0.1", InetAddress.getByName("::ffff:127.0.0.1").getHostAddress());

        InetAddress aAddr = InetAddress.getByName("224.0.0.0");
        assertEquals("224.0.0.0", aAddr.getHostAddress());


        try {
            InetAddress.getByName("1");
            fail();
        } catch (UnknownHostException expected) {
        }

        byte[] bAddr = {
            (byte) 0xFE, (byte) 0x80, (byte) 0x00, (byte) 0x00,
            (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
            (byte) 0x02, (byte) 0x11, (byte) 0x25, (byte) 0xFF,
            (byte) 0xFE, (byte) 0xF8, (byte) 0x7C, (byte) 0xB2
        };
        aAddr = Inet6Address.getByAddress(bAddr);
        String aString = aAddr.getHostAddress();
        assertTrue(aString.equals("fe80:0:0:0:211:25ff:fef8:7cb2") || aString.equals("fe80::211:25ff:fef8:7cb2"));

        byte[] cAddr = {
            (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF,
            (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF,
            (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF,
            (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF
        };
        aAddr = Inet6Address.getByAddress(cAddr);
        assertEquals("ffff:ffff:ffff:ffff:ffff:ffff:ffff:ffff", aAddr.getHostAddress());

        byte[] dAddr = {
            (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
            (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
            (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
            (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00
        };
        aAddr = Inet6Address.getByAddress(dAddr);
        aString = aAddr.getHostAddress();
        assertTrue(aString.equals("0:0:0:0:0:0:0:0") || aString.equals("::"));

        byte[] eAddr = {
            (byte) 0x00, (byte) 0x01, (byte) 0x02, (byte) 0x03,
            (byte) 0x04, (byte) 0x05, (byte) 0x06, (byte) 0x07,
            (byte) 0x08, (byte) 0x09, (byte) 0x0a, (byte) 0x0b,
            (byte) 0x0c, (byte) 0x0d, (byte) 0x0e, (byte) 0x0f
        };
        aAddr = Inet6Address.getByAddress(eAddr);
        assertEquals("1:203:405:607:809:a0b:c0d:e0f", aAddr.getHostAddress());

        byte[] fAddr = {
            (byte) 0x00, (byte) 0x10, (byte) 0x20, (byte) 0x30,
            (byte) 0x40, (byte) 0x50, (byte) 0x60, (byte) 0x70,
            (byte) 0x80, (byte) 0x90, (byte) 0xa0, (byte) 0xb0,
            (byte) 0xc0, (byte) 0xd0, (byte) 0xe0, (byte) 0xf0
        };
        aAddr = Inet6Address.getByAddress(fAddr);
        assertEquals("10:2030:4050:6070:8090:a0b0:c0d0:e0f0", aAddr.getHostAddress());
    }