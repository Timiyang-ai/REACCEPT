@Test
    public void testCreateBinaryString() {
        Ip4Prefix prefix;

        prefix = Ip4Prefix.valueOf("0.0.0.0/0");
        assertThat(RouteEntry.createBinaryString(prefix), is("0"));

        prefix = Ip4Prefix.valueOf("192.168.166.0/22");
        assertThat(RouteEntry.createBinaryString(prefix),
                   is("0" + "1100000010101000101001"));

        prefix = Ip4Prefix.valueOf("192.168.166.0/23");
        assertThat(RouteEntry.createBinaryString(prefix),
                   is("0" + "11000000101010001010011"));

        prefix = Ip4Prefix.valueOf("192.168.166.0/24");
        assertThat(RouteEntry.createBinaryString(prefix),
                   is("0" + "110000001010100010100110"));

        prefix = Ip4Prefix.valueOf("130.162.10.1/25");
        assertThat(RouteEntry.createBinaryString(prefix),
                   is("0" + "1000001010100010000010100"));

        prefix = Ip4Prefix.valueOf("255.255.255.255/32");
        assertThat(RouteEntry.createBinaryString(prefix),
                   is("0" + "11111111111111111111111111111111"));

        Ip6Prefix prefix6;
        Pattern pattern;
        Matcher matcher;

        prefix6 = Ip6Prefix.valueOf("::/0");
        assertThat(RouteEntry.createBinaryString(prefix6), is("0"));

        prefix6 = Ip6Prefix.valueOf("2000::1000/112");
        pattern = Pattern.compile("0" + "00100{108}");
        matcher = pattern.matcher(RouteEntry.createBinaryString(prefix6));
        assertTrue(matcher.matches());

        prefix6 = Ip6Prefix.valueOf("2000::1000/116");
        pattern = Pattern.compile("0" + "00100{108}0001");
        matcher = pattern.matcher(RouteEntry.createBinaryString(prefix6));
        assertTrue(matcher.matches());

        prefix6 = Ip6Prefix.valueOf("2000::2000/116");
        pattern = Pattern.compile("0" + "00100{108}0010");
        matcher = pattern.matcher(RouteEntry.createBinaryString(prefix6));
        assertTrue(matcher.matches());

        prefix6 = Ip6Prefix.valueOf("2000::1234/128");
        pattern = Pattern.compile("0" + "00100{108}0001001000110100");
        matcher = pattern.matcher(RouteEntry.createBinaryString(prefix6));
        assertTrue(matcher.matches());
    }