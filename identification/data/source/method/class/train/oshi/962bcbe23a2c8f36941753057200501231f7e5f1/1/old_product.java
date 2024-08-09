public static String hexStringToString(String hexString) {
        try {
            return new String(DatatypeConverter.parseHexBinary(hexString));
        } catch (IllegalArgumentException e) {
            // Hex failed to parse, just return the existing string
            return hexString;
        }
    }