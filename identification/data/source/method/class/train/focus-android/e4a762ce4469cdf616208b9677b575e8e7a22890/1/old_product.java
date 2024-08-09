public static boolean urlsMatchExceptForTrailingSlash(final @NonNull String url1, final @NonNull String url2) {
        int lengthDifference = url1.length() - url2.length();

        if (lengthDifference == 0) {
            // The simplest case:
            return url1.equalsIgnoreCase(url2);
        } else if (lengthDifference == 1) {
            // url1 is longer:
            return url1.charAt(url1.length() - 1) == '/' &&
                    url1.regionMatches(true, 0, url2, 0, url2.length());
        } else if (lengthDifference == -1) {
            return url2.charAt(url2.length() - 1) == '/' &&
                    url2.regionMatches(true, 0, url1, 0, url1.length());
        }

        return false;
    }