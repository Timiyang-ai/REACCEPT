public Object getValue() {
        try {
            return getMethod.invoke(instance, getArgs);
        } catch (final Throwable e) {
            throw new MethodProperty.MethodException(e);
        }
    }