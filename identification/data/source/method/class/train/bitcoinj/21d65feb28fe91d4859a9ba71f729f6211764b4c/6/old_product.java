public static Bech32Data decode(final String str) throws AddressFormatException {
        boolean lower = false, upper = false;
        if (str.length() < 8) throw new AddressFormatException("Input too short");
        if (str.length() > 90) throw new AddressFormatException("Input too long");
        for (int i = 0; i < str.length(); ++i) {
            char c = str.charAt(i);
            if (c < 33 || c > 126) throw new AddressFormatException("Characters out of range");
            if (c >= 'a' && c <= 'z') lower = true;
            if (c >= 'A' && c <= 'Z') upper = true;
        }
        if (lower && upper)  throw new AddressFormatException("Cannot mix upper and lower cases");
        int pos = str.lastIndexOf('1');
        if (pos < 1) throw new AddressFormatException("Missing human-readable part");
        if (pos + 7 > str.length()) throw new AddressFormatException("Data part too short");
        byte[] values = new byte[str.length() - 1 - pos];
        for (int i = 0; i < str.length() - 1 - pos; ++i) {
            char c = str.charAt(i + pos + 1);
            if (CHARSET_REV[c] == -1) throw new AddressFormatException("Characters out of range");
            values[i] = CHARSET_REV[c];
        }
        String hrp = str.substring(0, pos).toLowerCase(Locale.ROOT);
        if (!verifyChecksum(hrp, values)) throw new AddressFormatException("Invalid checksum");
        return new Bech32Data(hrp, Arrays.copyOfRange(values, 0, values.length - 6));
    }