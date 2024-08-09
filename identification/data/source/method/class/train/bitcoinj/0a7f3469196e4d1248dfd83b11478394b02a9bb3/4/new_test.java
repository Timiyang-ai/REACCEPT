    @Test(expected = AddressFormatException.InvalidDataLength.class)
    public void fromBase58_tooShort() {
        String base58 = Base58.encodeChecked(MAINNET.dumpedPrivateKeyHeader, new byte[31]);
        DumpedPrivateKey.fromBase58(null, base58);
    }