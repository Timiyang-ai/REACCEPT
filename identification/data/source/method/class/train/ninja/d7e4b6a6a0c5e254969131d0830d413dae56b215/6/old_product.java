private static String convertRawUriToRegex(String rawUri) {
        return "^" + rawUri.replaceAll("\\{.*?\\}", "(.*?)") + "$";
    }