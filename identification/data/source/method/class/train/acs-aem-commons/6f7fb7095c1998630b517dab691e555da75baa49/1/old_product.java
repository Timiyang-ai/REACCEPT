public static String toString(final Object obj, final Class<?> klass, String methodName) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        if (StringUtils.isBlank(methodName)) {
            methodName = "toString";
        }

        Method method = klass.getMethod(methodName);
        return (String) method.invoke(obj);
    }