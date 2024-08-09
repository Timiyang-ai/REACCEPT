public static String join(String[] parts, String separator) {
        if (parts.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (String part : parts) {
            sb.append(part);
            sb.append(separator);
        }
        return sb.substring(0, sb.length() - separator.length());
    }