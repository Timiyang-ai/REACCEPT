public static File base64StringToFile(final String encodedIsoData, final String folder, final String fileName) throws IOException {
        byte[] decoded = Base64.decodeBase64(encodedIsoData.getBytes(StandardCharsets.US_ASCII));
        Path destPath = Paths.get(folder, fileName);
        return Files.write(destPath, decoded).toFile();
    }