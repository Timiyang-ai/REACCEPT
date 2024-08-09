public T getValue() {
        try {
            return (T) getMethod.invoke(instance, getArgs);
        } catch (final Throwable e) {
            throw new MethodException(this, e);
        }
    }