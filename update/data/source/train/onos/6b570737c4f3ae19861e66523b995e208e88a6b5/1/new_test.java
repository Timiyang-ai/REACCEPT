@Test
    public void testCreateBinaryString() {
        Ip4Prefix prefix;

        prefix = Ip4Prefix.valueOf("0.0.0.0/0");
        assertThat(RouteEntry.createBinaryString(prefix), is(""));

        prefix = Ip4Prefix.valueOf("192.168.166.0/22");
        assertThat(RouteEntry.createBinaryString(prefix),
                   is("1100000010101000101001"));

        prefix = Ip4Prefix.valueOf("192.168.166.0/23");
        assertThat(RouteEntry.createBinaryString(prefix),
                   is("11000000101010001010011"));

        prefix = Ip4Prefix.valueOf("192.168.166.0/24");
        assertThat(RouteEntry.createBinaryString(prefix),
                   is("110000001010100010100110"));

        prefix = Ip4Prefix.valueOf("130.162.10.1/25");
        assertThat(RouteEntry.createBinaryString(prefix),
                   is("1000001010100010000010100"));

        prefix = Ip4Prefix.valueOf("255.255.255.255/32");
        assertThat(RouteEntry.createBinaryString(prefix),
                   is("11111111111111111111111111111111"));
    }