@Nonnull
    public static Map<String, String> getUserProperties(@Nonnull final Node node) {
        // Get node properties
        final PropertyIterator iterator;
        try {
            iterator = node.getProperties();
        } catch (AccessDeniedException e) {
            log.debug("Access denied", e);
            throw new AccessControlException(e.getMessage());
        } catch (RepositoryException e) {
            throw new MetadataRepositoryException("Failed to get properties for node: " + node, e);
        }

        // Convert iterator to map
        final String prefix = JcrMetadataAccess.USR_PREFIX + ":";
        final int prefixLength = prefix.length();
        final Map<String, String> properties = new HashMap<>((int) Math.min(iterator.getSize(), Integer.MAX_VALUE));

        while (iterator.hasNext()) {
            final Property property = iterator.nextProperty();
            try {
                if (property.getName().startsWith(prefix)) {
                    properties.put(URLDecoder.decode(property.getName().substring(prefixLength), USER_PROPERTY_ENCODING), property.getString());
                }
            } catch (AccessDeniedException e) {
            log.debug("Access denied", e);
            throw new AccessControlException(e.getMessage());
        } catch (RepositoryException e) {
                throw new MetadataRepositoryException("Failed to access property \"" + property + "\" on node: " + node, e);
            } catch (UnsupportedEncodingException e) {
                throw new IllegalStateException("Unsupported encoding for property \"" + property + "\" on node: " + node, e);
            }
        }

        return properties;
    }