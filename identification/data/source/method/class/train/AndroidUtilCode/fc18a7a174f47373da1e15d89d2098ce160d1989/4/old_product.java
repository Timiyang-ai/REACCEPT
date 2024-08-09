public static String base64Decode(String input) {
        return new String(Base64.decode(input, Base64.DEFAULT));
    }