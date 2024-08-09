public ReflectUtils field(final String name) {
        try {
            Field field = getAccessibleField(name);
            return new ReflectUtils(field.getType(), field.get(object));
        } catch (IllegalAccessException e) {
            throw new ReflectException(e);
        }
    }