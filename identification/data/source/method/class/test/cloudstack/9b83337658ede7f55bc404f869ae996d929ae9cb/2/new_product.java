public static String fileToBase64String(File isoFile) throws IOException {
        byte[] encoded = Base64.encodeBase64(FileUtils.readFileToByteArray(isoFile));
        return new String(encoded, StandardCharsets.US_ASCII);
    }