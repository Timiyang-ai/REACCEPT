public static String interpolate(File dockerFile, FixedStringSearchInterpolator interpolator) throws IOException {
        StringBuilder ret = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(dockerFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                ret.append(interpolator.interpolate(line)).append(System.lineSeparator());
            }
        }
        return ret.toString();
    }