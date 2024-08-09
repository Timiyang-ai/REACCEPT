public void setHeader(String header) {
        if (!CommonUtils.isBlank(header)) {
            checkHeaderNotInitialized();

            final String headerExpandedNewLines = ESCAPED_LINE_FEED_PATTERN
                    .matcher(header).replaceAll("\n");

            final Reader headerReader = new StringReader(headerExpandedNewLines);
            try {
                loadHeader(headerReader);
            }
            catch (final IOException ex) {
                throw new IllegalArgumentException("unable to load header", ex);
            }
            finally {
                Closeables.closeQuietly(headerReader);
            }
        }
    }