@Test
    public void testCreateBinaryString() {
        IpPrefix prefix;

        prefix = IpPrefix.valueOf("0.0.0.0/0");
        assertThat(RouteEntry.createBinaryString(prefix), is(""));

        prefix = IpPrefix.valueOf("192.168.166.0/22");
        assertThat(RouteEntry.createBinaryString(prefix),
                   is("1100000010101000101001"));

        prefix = IpPrefix.valueOf("192.168.166.0/23");
        assertThat(RouteEntry.createBinaryString(prefix),
                   is("11000000101010001010011"));

        prefix = IpPrefix.valueOf("192.168.166.0/24");
        assertThat(RouteEntry.createBinaryString(prefix),
                   is("110000001010100010100110"));

        prefix = IpPrefix.valueOf("130.162.10.1/25");
        assertThat(RouteEntry.createBinaryString(prefix),
                   is("1000001010100010000010100"));

        prefix = IpPrefix.valueOf("255.255.255.255/32");
        assertThat(RouteEntry.createBinaryString(prefix),
                   is("11111111111111111111111111111111"));
    }