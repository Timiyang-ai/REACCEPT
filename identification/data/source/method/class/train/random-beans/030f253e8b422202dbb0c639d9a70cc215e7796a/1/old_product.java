public Class<?> getWrapperType(Class<?> primitiveType) {
        // FIXME is there a better way to do this?
        if (Byte.TYPE.equals(primitiveType)) {
            return Byte.class;
        }
        if (Short.TYPE.equals(primitiveType)) {
            return Short.class;
        }
        if (Integer.TYPE.equals(primitiveType)) {
            return Integer.class;
        }
        if (Long.TYPE.equals(primitiveType)) {
            return Long.class;
        }
        if (Double.TYPE.equals(primitiveType)) {
            return Double.class;
        }
        if (Float.TYPE.equals(primitiveType)) {
            return Float.class;
        }
        if (Boolean.TYPE.equals(primitiveType)) {
            return Boolean.class;
        }
        if (Character.TYPE.equals(primitiveType)) {
            return Character.class;
        }
        return primitiveType; // if not primitive, return it as is
    }