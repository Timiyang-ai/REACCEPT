public static String getMd5(String input) {
        return getMd5(input.getBytes(StandardCharsets.UTF_8));
    }