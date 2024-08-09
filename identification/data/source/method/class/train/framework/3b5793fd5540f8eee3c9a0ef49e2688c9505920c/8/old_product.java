public Object getValue() {
        try {
            return getMethod.invoke(instance, getArgs);
        } catch (Throwable e) {
            throw new MethodProperty.MethodException(e);
        }
    }