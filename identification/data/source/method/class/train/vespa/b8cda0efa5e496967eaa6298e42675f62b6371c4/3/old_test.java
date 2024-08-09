    private static String getMd5(List<String> lines) {
        StringBuilder sb = new StringBuilder();
        for (String line : lines) {
            // Remove empty lines
            line = line.trim();
            if (line.length() > 0) {
                sb.append(line).append("\n");
            }
        }
        return ConfigUtils.getMd5(sb.toString());
    }