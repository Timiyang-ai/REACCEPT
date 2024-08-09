protected boolean accepts(SlingHttpServletRequest request) {
        if (!StringUtils.equalsIgnoreCase(POST_METHOD, request.getMethod())) {
            // Only POST methods are processed
            return false;
        } else if (!DAM_FOLDER_SHARE_OPERATION.equals(request.getParameter(OPERATION))) {
            // Only requests with :operation=dam.share.folder are processed
            return false;
        } else if (!StringUtils.startsWith(request.getResource().getPath(), DAM_PATH_PREFIX)) {
            // Only requests under /content/dam are processed
            return false;
        } else if (!request.getResource().isResourceType(JcrResourceConstants.NT_SLING_FOLDER)
                && !request.getResource().isResourceType(JcrResourceConstants.NT_SLING_ORDERED_FOLDER)) {
            // Only requests to sling:Folders or sling:Ordered folders are processed
            return false;
        }

        // If the above checks do not fail, treat as a valid request
        return true;
    }