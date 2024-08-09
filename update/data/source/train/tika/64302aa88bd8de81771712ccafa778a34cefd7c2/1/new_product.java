public void add(final Property property, final String value) {

        if (property == null) {
            throw new NullPointerException("property must not be null");
        }
        if (property.getPropertyType() == PropertyType.COMPOSITE) {
            add(property.getPrimaryProperty(), value);
            if (property.getSecondaryExtractProperties() != null) {
                for (Property secondaryExtractProperty : property.getSecondaryExtractProperties()) {
                    add(secondaryExtractProperty, value);
                }
            }
        } else {
            String[] values = metadata.get(property.getName());

            if (values == null) {
                set(property, value);
            } else {
                if (property.isMultiValuePermitted()) {
                    set(property, appendedValues(values, value));
                } else {
                    throw new PropertyTypeException(property.getName() +
                            " : " + property.getPropertyType());
                }
            }
        }
    }