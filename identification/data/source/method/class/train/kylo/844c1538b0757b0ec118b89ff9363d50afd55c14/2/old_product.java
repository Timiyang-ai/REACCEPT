public static void setUserProperties(@Nonnull final Node node, @Nonnull final Set<FieldDescriptor> fields, @Nonnull final Map<String, String> properties) {
        // Verify required properties are not empty
        for (final FieldDescriptor field : fields) {
            if (field.isRequired() && StringUtils.isEmpty(properties.get(field.getName()))) {
                throw new MissingUserPropertyException("Missing required property: " + field.getName());
            }
        }

        // Set properties on node
        final Set<String> newProperties = new HashSet<>(properties.size());
        final String prefix = JcrMetadataAccess.USR_PREFIX + ":";

        properties.forEach((key, value) -> {
            try {
                final String name = prefix + URLEncoder.encode(key, USER_PROPERTY_ENCODING);
                newProperties.add(name);
                node.setProperty(name, value);
            } catch (RepositoryException e) {
                throw new MetadataRepositoryException("Failed to set user property \"" + key + "\" on node: " + node, e);
            } catch (UnsupportedEncodingException e) {
                throw new IllegalStateException(e.toString(), e);
            }
        });

        // Get node properties
        final PropertyIterator iterator;
        try {
            iterator = node.getProperties();
        } catch (RepositoryException e) {
            throw new MetadataRepositoryException("Failed to get properties for node: " + node, e);
        }

        // Remove properties from node
        while (iterator.hasNext()) {
            final Property property = iterator.nextProperty();
            try {
                final String name = property.getName();
                if (name.startsWith(prefix) && !newProperties.contains(name)) {
                    property.remove();
                }
            } catch (RepositoryException e) {
                throw new MetadataRepositoryException("Failed to remove property \"" + property + "\" on node: " + node, e);
            }
        }
    }