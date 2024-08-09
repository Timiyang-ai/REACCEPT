@SuppressWarnings("unchecked")
    public void setValue(Object newValue) throws Property.ReadOnlyException,
            Property.ConversionException {

        // Checks the mode
        if (isReadOnly()) {
            throw new Property.ReadOnlyException();
        }

        // Checks the type of the value
        if (newValue != null && !type.isAssignableFrom(newValue.getClass())) {
            throw new Property.ConversionException(
                    "Invalid value type for ObjectProperty.");
        }

        invokeSetMethod((T) newValue);
        fireValueChange();
    }