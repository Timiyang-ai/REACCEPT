public ReflectUtils field(String name, Object value) {
        try {
            Field field = getAccessibleField(name);
            if ((field.getModifiers() & Modifier.FINAL) == Modifier.FINAL) {
                Field modifiersField = Field.class.getDeclaredField("modifiers");
                modifiersField.setAccessible(true);
                modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
            }
            field.set(object, unwrap(value));
            return this;
        } catch (Exception e) {
            throw new ReflectException(e);
        }
    }