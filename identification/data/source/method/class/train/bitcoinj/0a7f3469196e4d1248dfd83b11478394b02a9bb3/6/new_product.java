public static SegwitAddress fromBech32(@Nullable NetworkParameters params, String bech32)
            throws AddressFormatException {
        Bech32.Bech32Data bechData = Bech32.decode(bech32);
        if (params == null) {
            for (NetworkParameters p : Networks.get()) {
                if (bechData.hrp.equals(p.getSegwitAddressHrp()))
                    return new SegwitAddress(p, bechData.data);
            }
            throw new AddressFormatException.InvalidPrefix("No network found for " + bech32);
        } else {
            if (bechData.hrp.equals(params.getSegwitAddressHrp()))
                return new SegwitAddress(params, bechData.data);
            throw new AddressFormatException.WrongNetwork(bechData.hrp);
        }
    }