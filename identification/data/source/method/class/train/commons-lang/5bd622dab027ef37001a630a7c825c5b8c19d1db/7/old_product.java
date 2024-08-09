public static <T> String reflectionToString(
        T object,
        ToStringStyle style,
        boolean outputTransients,
        Class<? super T> reflectUpToClass) {
        return ReflectionToStringBuilder.toString(object, style, outputTransients, false, reflectUpToClass);
    }