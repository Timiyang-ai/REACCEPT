@Override
    public T getValue() {
        try {
            if (instance == null) {
                return null;
            } else {
                return (T) getMethod.invoke(instance, getArgs);
            }
        } catch (final Throwable e) {
            throw new MethodException(this, e);
        }
    }