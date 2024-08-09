@Test
    public void testMakeMaskedAddressIPv4() {
        Ip4Address ipAddress = Ip4Address.valueOf("1.2.3.5");
        Ip4Address ipAddressMasked;

        ipAddressMasked = Ip4Address.makeMaskedAddress(ipAddress, 24);
        assertThat(ipAddressMasked.toString(), is("1.2.3.0"));

        ipAddressMasked = Ip4Address.makeMaskedAddress(ipAddress, 0);
        assertThat(ipAddressMasked.toString(), is("0.0.0.0"));

        ipAddressMasked = Ip4Address.makeMaskedAddress(ipAddress, 32);
        assertThat(ipAddressMasked.toString(), is("1.2.3.5"));
    }