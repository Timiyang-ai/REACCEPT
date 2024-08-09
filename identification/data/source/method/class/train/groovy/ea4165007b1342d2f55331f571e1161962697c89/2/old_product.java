public static String md5(CharSequence self) throws NoSuchAlgorithmException {
        final String text = self.toString();

        return md5(text.getBytes(StandardCharsets.UTF_8));
    }