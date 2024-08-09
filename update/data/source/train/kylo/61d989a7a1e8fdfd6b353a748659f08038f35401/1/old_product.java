@Nonnull
    public static URL parseUrl(@Nonnull final String s, @Nullable final Configuration conf) {
        // Parse as a URI
        URI uri = URI.create(s);

        if (uri.getScheme() == null) {
            uri = ((conf != null) ? FileSystem.getDefaultUri(conf) : URI.create("file:///")).resolve(uri);
        }
        if (!IGNORED_PROTOCOLS.contains(uri.getScheme())) {
            uri = URI.create("hadoop:" + uri);
        }

        // Create the URL
        try {
            return uri.toURL();
        } catch (final MalformedURLException e) {
            throw new IllegalArgumentException(e);
        }
    }