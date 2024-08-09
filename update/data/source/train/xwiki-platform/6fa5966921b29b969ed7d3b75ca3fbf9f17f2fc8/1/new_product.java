protected File getTemporaryFile(String uri, XWikiContext context)
    {
        Matcher matcher = URI_PATTERN.matcher(uri);
        File result = null;
        if (matcher.find()) {
            String wiki = context.getDatabase();
            try {
                wiki = URLEncoder.encode(wiki, URL_ENCODING);
            } catch (UnsupportedEncodingException e) {
                // This should never happen;
            }
            String space = withMinimalURLEncoding(matcher.group(1));
            String page = withMinimalURLEncoding(matcher.group(2));
            String module = withMinimalURLEncoding(matcher.group(3));
            // The file path is used as is, without any modifications (no decoding/encoding is performed). The modules
            // that create the temporary files and the corresponding URLs used to access them are responsible for
            // encoding the file path components so that they don't contain invalid characters.
            String filePath = matcher.group(4);
            String prefix = String.format("temp/%s/%s/%s/%s/", module, wiki, space, page);
            String path = URI.create(prefix + filePath).normalize().toString();
            if (path.startsWith(prefix)) {
                result = new File(this.environment.getTemporaryDirectory(), path);
                result = result.exists() ? result : null;
            }
        }
        return result;
    }