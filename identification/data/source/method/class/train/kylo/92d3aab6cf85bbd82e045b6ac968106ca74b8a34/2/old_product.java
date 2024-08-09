@Nonnull
    @SafeVarargs
    public static Set<UserProperty> toUserProperties(@Nonnull final Map<String, String> properties, @Nonnull final Set<UserFieldDescriptor> ... userFields) {
        // Map field names and order
        int order = 0;
        int size = Arrays.stream(userFields).collect(Collectors.summingInt(Set::size));
        final Map<String, UserFieldDescriptor> userFieldMap = Maps.newHashMapWithExpectedSize(size);
        final Map<String, Integer> userFieldOrder = Maps.newHashMapWithExpectedSize(size);

        for (final Set<UserFieldDescriptor> set : userFields) {
            final Iterator<UserFieldDescriptor> sorted = set.stream().sorted((f1, f2) -> Integer.compare(f1.getOrder(), f2.getOrder())).iterator();
            while (sorted.hasNext()) {
                final UserFieldDescriptor field = sorted.next();
                if (!userFieldMap.containsKey(field.getSystemName())) {
                    userFieldMap.put(field.getSystemName(), field);
                    userFieldOrder.put(field.getSystemName(), order++);
                }
            }
        }

        // Convert from Metadata to Feed Manager format
        return properties.entrySet().stream()
                .map(entry -> {
                    // Create the Feed Manager property
                    final UserProperty property = new UserProperty();
                    property.setLocked(false);
                    property.setSystemName(entry.getKey());
                    property.setValue(entry.getValue());

                    // Set additional Metadata attributes
                    final UserFieldDescriptor field = userFieldMap.get(entry.getKey());
                    if (field != null) {
                        property.setDescription(field.getDescription());
                        property.setDisplayName(field.getDisplayName());
                        property.setLocked(true);
                        property.setOrder(userFieldOrder.get(entry.getKey()));
                        property.setRequired(field.isRequired());
                    }

                    // Return the Feed Manager property
                    return property;
                })
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }