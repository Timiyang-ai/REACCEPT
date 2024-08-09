public void add(final Property property, final String value) {
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