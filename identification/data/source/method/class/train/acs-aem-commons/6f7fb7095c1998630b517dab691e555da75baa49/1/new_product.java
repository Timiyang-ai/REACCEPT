public static String toString(final Object obj, final Class<?> klass, String methodName)
            throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        if (StringUtils.isBlank(methodName)) {
            methodName = "toString";
        }

        boolean isPrimitiveOrWrapped =
                obj.getClass().isPrimitive() || ClassUtils.wrapperToPrimitive(obj.getClass()) != null;

        if (isPrimitiveOrWrapped) {
            return String.valueOf(obj);
        } else if (Date.class.equals(klass)) {
            return ((Date) obj).toString();
        } else if (Calendar.class.equals(klass)) {
            return ((Calendar) obj).getTime().toString();
        } else if(isArray(obj)) {
            return toStringFromArray(obj);
        } else {
            Method method = klass.getMethod(methodName);
            return (String) method.invoke(obj);
        }
    }