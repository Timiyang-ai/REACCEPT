public static Bech32Data decode(final String str) throws AddressFormatException {
        boolean lower = false, upper = false;
        if (str.length() < 8) throw new AddressFormatException("Input too short");
        if (str.length() > 90) throw new AddressFormatException("Input too long");
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
        if (pos < 1) throw new AddressFormatException("Missing human-readable part");
        if (pos + 7 > str.length()) throw new AddressFormatException("Data part too short");
        byte[] values = new byte[str.length() - 1 - pos];
        for (int i = 0; i < str.length() - 1 - pos; ++i) {
            char c = str.charAt(i + pos + 1);
            if (CHARSET_REV[c] == -1) throw new AddressFormatException.InvalidCharacter(c, i + pos + 1);
            values[i] = CHARSET_REV[c];
        }
        String hrp = str.substring(0, pos).toLowerCase(Locale.ROOT);
        if (!verifyChecksum(hrp, values)) throw new AddressFormatException.InvalidChecksum();
        return new Bech32Data(hrp, Arrays.copyOfRange(values, 0, values.length - 6));
    }