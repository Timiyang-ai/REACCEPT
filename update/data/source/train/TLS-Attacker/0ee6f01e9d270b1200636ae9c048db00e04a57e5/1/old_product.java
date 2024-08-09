private static boolean containsModifiableVariableModification(ModifiableVariableHolder object, Field field) {
	try {
	    field.setAccessible(true);
	    ModifiableVariable mv = (ModifiableVariable) field.get(object);
	    return (mv != null && mv.getModification() != null);
	} catch (IllegalAccessException | IllegalArgumentException ex) {
	    throw new ModificationException(ex.getLocalizedMessage(), ex);
	}
    }