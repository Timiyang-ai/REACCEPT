public void doDynamic(StaplerRequest req, StaplerResponse rsp) throws IOException, ServletException {
        String path = req.getRestOfPath();

        if (path.startsWith("/META-INF/") || path.startsWith("/WEB-INF/")) {
            throw HttpResponses.notFound();
        }

        if(path.length()==0)
            path = "/";

        // Stapler routes requests like the "/static/.../foo/bar/zot" to be treated like "/foo/bar/zot"
        // and this is used to serve long expiration header, by using Jenkins.VERSION_HASH as "..."
        // to create unique URLs. Recognize that and set a long expiration header.
        String requestPath = req.getRequestURI().substring(req.getContextPath().length());
        boolean staticLink = requestPath.startsWith("/static/");

        long expires = staticLink ? TimeUnit2.DAYS.toMillis(365) : -1;

        // use serveLocalizedFile to support automatic locale selection
        try {
            rsp.serveLocalizedFile(req, wrapper.baseResourceURL.toURI().resolve(new URI(null, '.' + path, null)).toURL(), expires);
        } catch (URISyntaxException x) {
            throw new IOException(x);
        }
    }