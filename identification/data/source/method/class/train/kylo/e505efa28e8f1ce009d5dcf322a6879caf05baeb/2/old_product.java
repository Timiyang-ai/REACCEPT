@Nonnull
    public static Map<String, String> getUserProperties(@Nonnull final Node node) {
        // Get node properties
        final PropertyIterator iterator;
        try {
            iterator = node.getProperties();
        } catch (RepositoryException e) {
            throw new MetadataRepositoryException("Failed to get properties for node: " + node, e);
        }

        // Convert iterator to map
        final int prefixLength = USER_PROPERTY_PREFIX.length();
        final Map<String, String> properties = new HashMap<>((int) Math.min(iterator.getSize(), Integer.MAX_VALUE));

        while (iterator.hasNext()) {
            final Property property = iterator.nextProperty();
            try {
                if (property.getName().startsWith(USER_PROPERTY_PREFIX)) {
                    properties.put(URLDecoder.decode(property.getName().substring(prefixLength), USER_PROPERTY_ENCODING), property.getString());
                }
            } catch(RepositoryException e){
                throw new MetadataRepositoryException("Failed to access property \"" + property + "\" on node: " + node, e);
            } catch(UnsupportedEncodingException e){
                throw new IllegalStateException("Unsupported encoding for property \"" + property + "\" on node: " + node, e);
            }
        }

        return properties;
    }