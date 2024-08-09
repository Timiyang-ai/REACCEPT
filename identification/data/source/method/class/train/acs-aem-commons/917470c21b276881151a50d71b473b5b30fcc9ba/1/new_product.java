@Override
    public boolean accepts(SlingHttpServletRequest request) throws HttpCacheReposityAccessException {

        // Match authentication requirement.
        if (UserUtils.isAnonymous(request.getResourceResolver().getUserID())) {
            if (AuthenticationStatusConfigConstants.AUTHENTICATED_REQUEST.equals(this.authenticationRequirement)) {
                // Request is anonymous but the config accepts only authenticated request and hence reject.
                return false;
            }
        } else {
            if (AuthenticationStatusConfigConstants.ANONYMOUS_REQUEST.equals(this.authenticationRequirement)) {
                // Request is authenticated but config is for anonymous and hence reject.
                return false;
            }
        }


        // Match request URI.
        final String uri = request.getRequestURI();
        if (!this.matches(this.requestUriPatternsAsRegEx, uri)) {
            // Does not match URI Whitelist
            return false;
        }

        // Match blacklisted URI.
        if (this.matches(this.blacklistedRequestUriPatternsAsRegEx, uri)) {
            // Matches URI Blacklist; reject
            return false;
        }

        // Match groups.
        if (UserUtils.isAnonymous(request.getResourceResolver().getUserID())) {
            // If the user is anonymous, no matching with groups required.
            return true;
        } else {
            // Case of authenticated requests.
            if (this.userGroups.size() > 0) {
                try {
                    List<String> requestUserGroupNames = UserUtils.getUserGroupMembershipNames(request
                            .getResourceResolver().adaptTo(User.class));

                    // At least one of the group in config should match.
                    boolean isGroupMatchFound = CollectionUtils.containsAny(this.userGroups, requestUserGroupNames);
                    if (!isGroupMatchFound) {
                        log.debug("Group didn't match and hence rejecting the cache config.");
                    }
                    return isGroupMatchFound;
                } catch (RepositoryException e) {
                    throw new HttpCacheReposityAccessException("Unable to access group information of request user.",
                            e);
                }
            }
        }
        return true;
    }