@Override
    public boolean accepts(SlingHttpServletRequest request) {
        if ("anonymous".equals(request.getResourceResolver().getUserID())
                && AuthenticationStatusConfigConstants.AUTHENTICATED_REQUEST.equals(this.authenticationRequirement)) {
            // Only supports authenticated requests, but request is anonymous so reject
            return false;
        }

        final String uri = request.getRequestURI();

        if (!this.matches(this.requestUriPatternsAsRegEx, uri)) {
            // Does not match URI Whitelist
            return false;
        }

        if (this.matches(this.blacklistedRequestUriPatternsAsRegEx, uri)) {
            // Matches URI Blacklist; reject
            return false;
        }

        return true;
    }