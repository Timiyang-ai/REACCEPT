public void setValue(Object newValue) throws Property.ReadOnlyException,
            Property.ConversionException {

        // Checks the mode
        if (isReadOnly()) {
            throw new Property.ReadOnlyException();
        }

        Object value = convertValue(newValue, type);

        invokeSetMethod(value);
        fireValueChange();
    }