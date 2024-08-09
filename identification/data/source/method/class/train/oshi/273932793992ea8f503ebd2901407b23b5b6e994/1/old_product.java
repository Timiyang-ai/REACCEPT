public static List<String> readFile(String filename) throws IOException {
        LOG.debug("Reading file {}", filename);
        return Files.readAllLines(Paths.get(filename), StandardCharsets.UTF_8);
    }