@Deprecated
    public static Throwable getCause(final Throwable throwable) {
        return getCause(throwable, CAUSE_METHOD_NAMES);
    }