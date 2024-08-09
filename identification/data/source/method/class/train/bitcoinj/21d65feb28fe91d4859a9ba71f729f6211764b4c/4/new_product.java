public static BIP38PrivateKey fromBase58(NetworkParameters params, String base58) throws AddressFormatException {
        byte[] versionAndDataBytes = Base58.decodeChecked(base58);
        int version = versionAndDataBytes[0] & 0xFF;
        byte[] bytes = Arrays.copyOfRange(versionAndDataBytes, 1, versionAndDataBytes.length);

        if (version != 0x01)
            throw new AddressFormatException("Mismatched version number: " + version);
        if (bytes.length != 38)
            throw new AddressFormatException("Wrong number of bytes, excluding version byte: " + bytes.length);
        boolean hasLotAndSequence = (bytes[1] & 0x04) != 0; // bit 2
        boolean compressed = (bytes[1] & 0x20) != 0; // bit 5
        if ((bytes[1] & 0x01) != 0) // bit 0
            throw new AddressFormatException("Bit 0x01 reserved for future use.");
        if ((bytes[1] & 0x02) != 0) // bit 1
            throw new AddressFormatException("Bit 0x02 reserved for future use.");
        if ((bytes[1] & 0x08) != 0) // bit 3
            throw new AddressFormatException("Bit 0x08 reserved for future use.");
        if ((bytes[1] & 0x10) != 0) // bit 4
            throw new AddressFormatException("Bit 0x10 reserved for future use.");
        final int byte0 = bytes[0] & 0xff;
        final boolean ecMultiply;
        if (byte0 == 0x42) {
            // Non-EC-multiplied key
            if ((bytes[1] & 0xc0) != 0xc0) // bits 6+7
                throw new AddressFormatException("Bits 0x40 and 0x80 must be set for non-EC-multiplied keys.");
            ecMultiply = false;
            if (hasLotAndSequence)
                throw new AddressFormatException("Non-EC-multiplied keys cannot have lot/sequence.");
        } else if (byte0 == 0x43) {
            // EC-multiplied key
            if ((bytes[1] & 0xc0) != 0x00) // bits 6+7
                throw new AddressFormatException("Bits 0x40 and 0x80 must be cleared for EC-multiplied keys.");
            ecMultiply = true;
        } else {
            throw new AddressFormatException("Second byte must by 0x42 or 0x43.");
        }
        byte[] addressHash = Arrays.copyOfRange(bytes, 2, 6);
        byte[] content = Arrays.copyOfRange(bytes, 6, 38);
        return new BIP38PrivateKey(params, bytes, ecMultiply, compressed, hasLotAndSequence, addressHash, content);
    }