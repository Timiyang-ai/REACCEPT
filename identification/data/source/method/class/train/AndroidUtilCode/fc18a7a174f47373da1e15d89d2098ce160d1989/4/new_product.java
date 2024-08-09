public static byte[] base64Decode(String input) {
        return Base64.decode(input, Base64.NO_WRAP);
    }