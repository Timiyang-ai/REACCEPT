@Override
    public void setValue(T newValue) throws Property.ReadOnlyException {

        // Checks the mode
        if (isReadOnly()) {
            throw new Property.ReadOnlyException();
        }

        invokeSetMethod(newValue);
        fireValueChange();
    }