public void setValue(Object newValue) throws Property.ReadOnlyException,
			Property.ConversionException {

		// Checks the mode
		if (isReadOnly())
			throw new Property.ReadOnlyException();

		// Try to assign the compatible value directly
		if (newValue == null || type.isAssignableFrom(newValue.getClass()))
			invokeSetMethod(newValue);

		// Otherwise try to convert the value trough string constructor
		else {

			Object value;
			try {

				// Gets the string constructor
				Constructor constr = getType().getConstructor(
						new Class[] { String.class });

				value = constr
						.newInstance(new Object[] { newValue.toString() });

			} catch (java.lang.Exception e) {
				throw new Property.ConversionException(e);
			}

			// Creates new object from the string
			invokeSetMethod(value);
		}
	}