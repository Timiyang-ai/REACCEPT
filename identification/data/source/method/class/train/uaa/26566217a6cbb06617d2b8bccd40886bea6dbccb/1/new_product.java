public static String findMatchingRedirectUri(Collection<String> redirectUris, String requestedRedirectUri, String fallbackRedirectUri) {
        AntPathMatcher matcher = new AntPathMatcher();

        for (String pattern : ofNullable(redirectUris).orElse(emptyList())) {
            if (matcher.match(pattern, requestedRedirectUri)) {
                return requestedRedirectUri;
            }
        }

        return ofNullable(fallbackRedirectUri).orElse(requestedRedirectUri);
    }