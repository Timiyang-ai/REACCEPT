public Object convert(Object existingDestinationFieldValue, Object sourceFieldValue, Class<?> destinationClass, Class<?> sourceClass) {
    if (prototypeA.equals(destinationClass)) {
      return convertFrom((B) sourceFieldValue, (A) existingDestinationFieldValue);
    } else if (prototypeB.equals(destinationClass)) {
      return convertTo((A) sourceFieldValue, (B) existingDestinationFieldValue);
    } else if (prototypeA.equals(sourceClass)) {
      return convertTo((A) sourceFieldValue, (B) existingDestinationFieldValue);
    } else if (prototypeB.equals(sourceClass)) {
      return convertFrom((B) sourceFieldValue, (A) existingDestinationFieldValue);
    } else if (prototypeA.isAssignableFrom(destinationClass)) {
      return convertFrom((B) sourceFieldValue, (A) existingDestinationFieldValue);
    } else if (prototypeB.isAssignableFrom(destinationClass)) {
      return convertTo((A) sourceFieldValue, (B) existingDestinationFieldValue);
    } else if (prototypeA.isAssignableFrom(sourceClass)) {
      return convertTo((A) sourceFieldValue, (B) existingDestinationFieldValue);
    } else if (prototypeB.isAssignableFrom(sourceClass)) {
      return convertFrom((B) sourceFieldValue, (A) existingDestinationFieldValue);
    } else {
      throw new MappingException("Destination Type (" + destinationClass.getName()
          + ") is not accepted by this Custom Converter (" 
          + this.getClass().getName() + ")!");
    }
  }