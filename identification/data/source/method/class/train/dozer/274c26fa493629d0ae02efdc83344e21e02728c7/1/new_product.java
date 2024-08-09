public Object convert(Object existingDestinationFieldValue, Object sourceFieldValue, Class<?> destinationClass, Class<?> sourceClass) {
    Class<?> wrappedDestinationClass = ClassUtils.primitiveToWrapper(destinationClass);
    Class<?> wrappedSourceClass = ClassUtils.primitiveToWrapper(sourceClass);

    if (prototypeA.equals(wrappedDestinationClass)) {
      return convertFrom((B) sourceFieldValue, (A) existingDestinationFieldValue);
    } else if (prototypeB.equals(wrappedDestinationClass)) {
      return convertTo((A) sourceFieldValue, (B) existingDestinationFieldValue);
    } else if (prototypeA.equals(wrappedSourceClass)) {
      return convertTo((A) sourceFieldValue, (B) existingDestinationFieldValue);
    } else if (prototypeB.equals(wrappedSourceClass)) {
      return convertFrom((B) sourceFieldValue, (A) existingDestinationFieldValue);
    } else if (prototypeA.isAssignableFrom(wrappedDestinationClass)) {
      return convertFrom((B) sourceFieldValue, (A) existingDestinationFieldValue);
    } else if (prototypeB.isAssignableFrom(wrappedDestinationClass)) {
      return convertTo((A) sourceFieldValue, (B) existingDestinationFieldValue);
    } else if (prototypeA.isAssignableFrom(wrappedSourceClass)) {
      return convertTo((A) sourceFieldValue, (B) existingDestinationFieldValue);
    } else if (prototypeB.isAssignableFrom(wrappedSourceClass)) {
      return convertFrom((B) sourceFieldValue, (A) existingDestinationFieldValue);
    } else {
      throw new MappingException("Destination Type (" + wrappedDestinationClass.getName()
          + ") is not accepted by this Custom Converter (" 
          + this.getClass().getName() + ")!");
    }

  }