public static DumpedPrivateKey fromBase58(@Nullable NetworkParameters params, String base58)
            throws AddressFormatException {
        byte[] versionAndDataBytes = Base58.decodeChecked(base58);
        int version = versionAndDataBytes[0] & 0xFF;
        byte[] bytes = Arrays.copyOfRange(versionAndDataBytes, 1, versionAndDataBytes.length);
        if (params == null) {
            for (NetworkParameters p : Networks.get())
                if (version == p.getDumpedPrivateKeyHeader())
                    return new DumpedPrivateKey(p, bytes);
            throw new AddressFormatException("No network found for version " + version);
        } else {
            if (version == params.getDumpedPrivateKeyHeader())
                return new DumpedPrivateKey(params, bytes);
            throw new WrongNetworkException(version);
        }
    }