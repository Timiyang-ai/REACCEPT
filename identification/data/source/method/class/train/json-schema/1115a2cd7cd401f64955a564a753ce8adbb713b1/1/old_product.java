public SchemaLoaderBuilder resolutionScope(final String id) {
            try {
                return resolutionScope(new URI(id));
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }
        }