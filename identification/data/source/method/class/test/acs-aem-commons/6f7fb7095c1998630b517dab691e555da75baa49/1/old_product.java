public static String toString(final Object obj)
            throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {

        boolean isPrimitiveOrWrapped =
                obj.getClass().isPrimitive() || ClassUtils.wrapperToPrimitive(obj.getClass()) != null;

        if (isPrimitiveOrWrapped) {
            return String.valueOf(obj);
        } else if (obj instanceof Date) {
            return ((Date) obj).toString();
        } else if (obj instanceof Calendar) {
            return ((Calendar) obj).getTime().toString();
        } else if(isArray(obj)) {
            return toStringFromArray(obj);
        } else {
            Method method = obj.getClass().getMethod("toString");
            return (String) method.invoke(obj);
        }
    }