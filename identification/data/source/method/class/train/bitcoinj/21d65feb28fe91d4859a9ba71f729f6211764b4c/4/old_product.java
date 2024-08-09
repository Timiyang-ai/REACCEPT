public static BIP38PrivateKey fromBase58(NetworkParameters params, String base58) throws AddressFormatException {
        return new BIP38PrivateKey(params, base58);
    }