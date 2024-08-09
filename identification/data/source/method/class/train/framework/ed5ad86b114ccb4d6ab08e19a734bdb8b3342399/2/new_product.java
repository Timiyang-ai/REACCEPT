public static String join(String[] parts, String separator) {
        if (parts.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < parts.length; i++) {
            sb.append(parts[i]);
            sb.append(separator);
        }
        return sb.substring(0, sb.length() - separator.length());
    }