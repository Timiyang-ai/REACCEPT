public static String interpolate(File dockerFile, Properties properties, String filter) throws IOException {
        StringBuilder ret = new StringBuilder();
        String[] delimiters = extractDelimiters(filter);
        try (BufferedReader reader = new BufferedReader(new FileReader(dockerFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                ret.append(interpolateLine(line, properties, delimiters)).append("\n");
            }
        }
        return ret.toString();
    }