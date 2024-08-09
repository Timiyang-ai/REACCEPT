public static <T> String reflectionToString(
        final T object,
        final ToStringStyle style,
        final boolean outputTransients,
        final Class<? super T> reflectUpToClass) {
        return ReflectionToStringBuilder.toString(object, style, outputTransients, false, reflectUpToClass);
    }