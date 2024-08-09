public static byte[] base64Decode(final String input) {
        if (input == null || input.length() == 0) return new byte[0];
        return Base64.decode(input, Base64.NO_WRAP);
    }