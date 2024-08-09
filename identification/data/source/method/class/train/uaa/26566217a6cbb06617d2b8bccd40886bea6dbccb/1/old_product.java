public static String findMatchingRedirectUri(Collection<String> redirectUris, String requestedRedirectUri, String fallbackRedirectUri) {
        AntPathMatcher matcher = new AntPathMatcher();

        if (redirectUris == null) {
            return requestedRedirectUri;
        }

        for (String pattern : redirectUris) {
            if (matcher.match(pattern, requestedRedirectUri)) {
                return requestedRedirectUri;
            }
        }

        return fallbackRedirectUri;
    }