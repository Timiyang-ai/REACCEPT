@Test
    public void testMakeMaskedAddress() {
        Ip4Address ip4Address = new Ip4Address("1.2.3.5");
        Ip4Address ip4AddressMasked =
            Ip4Address.makeMaskedAddress(ip4Address, 24);
        assertThat(ip4AddressMasked.toString(), is("1.2.3.0"));

        ip4AddressMasked = Ip4Address.makeMaskedAddress(ip4Address, 0);
        assertThat(ip4AddressMasked.toString(), is("0.0.0.0"));

        ip4AddressMasked = Ip4Address.makeMaskedAddress(ip4Address, 32);
        assertThat(ip4AddressMasked.toString(), is("1.2.3.5"));
    }