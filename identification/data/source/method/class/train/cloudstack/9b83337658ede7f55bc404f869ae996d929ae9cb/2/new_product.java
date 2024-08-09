public static File base64StringToFile(String encodedIsoData, String folder, String fileName) throws IOException {
        byte[] decoded = Base64.decodeBase64(encodedIsoData.getBytes(StandardCharsets.US_ASCII));
        Path destPath = Paths.get(folder, fileName);
        try {
            Files.createDirectories(destPath.getParent());
        } catch (final IOException e) {
            LOG.warn("Exception hit while trying to recreate directory: " + destPath.getParent().toString());
        }
        return Files.write(destPath, decoded).toFile();
    }