    @Test(expected = AddressFormatException.InvalidChecksum.class)
    public void decode_invalidNetwork() {
        Bech32.decode("A12UEL5X");
    }