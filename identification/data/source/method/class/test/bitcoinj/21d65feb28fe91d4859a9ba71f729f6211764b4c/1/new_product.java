public static Bech32Data decode(final String str) throws AddressFormatException {
        boolean lower = false, upper = false;
        if (str.length() < 8)
            throw new AddressFormatException.InvalidDataLength("Input too short: " + str.length());
        if (str.length() > 90)
            throw new AddressFormatException.InvalidDataLength("Input too long: " + str.length());
        for (int i = 0; i < str.length(); ++i) {
            char c = str.charAt(i);
            if (c < 33 || c > 126) throw new AddressFormatException.InvalidCharacter(c, i);
            if (c >= 'a' && c <= 'z') {
                if (upper)
                    throw new AddressFormatException.InvalidCharacter(c, i);
                lower = true;
            }
            if (c >= 'A' && c <= 'Z') {
                if (lower)
                    throw new AddressFormatException.InvalidCharacter(c, i);
                upper = true;
            }
        }
        final int pos = str.lastIndexOf('1');
        if (pos < 1) throw new AddressFormatException.InvalidPrefix("Missing human-readable part");
        final int dataPartLength = str.length() - 1 - pos;
        if (dataPartLength < 6) throw new AddressFormatException.InvalidDataLength("Data part too short: " + dataPartLength);
        byte[] values = new byte[dataPartLength];
        for (int i = 0; i < dataPartLength; ++i) {
            char c = str.charAt(i + pos + 1);
            if (CHARSET_REV[c] == -1) throw new AddressFormatException.InvalidCharacter(c, i + pos + 1);
            values[i] = CHARSET_REV[c];
        }
        String hrp = str.substring(0, pos).toLowerCase(Locale.ROOT);
        if (!verifyChecksum(hrp, values)) throw new AddressFormatException.InvalidChecksum();
        return new Bech32Data(hrp, Arrays.copyOfRange(values, 0, values.length - 6));
    }