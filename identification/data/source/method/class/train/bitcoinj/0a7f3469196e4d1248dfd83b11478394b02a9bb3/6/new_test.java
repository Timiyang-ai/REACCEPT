    @Test(expected = AddressFormatException.InvalidDataLength.class)
    public void fromBech32_tooShort() {
        SegwitAddress.fromBech32(null, "bc1rw5uspcuh");
    }