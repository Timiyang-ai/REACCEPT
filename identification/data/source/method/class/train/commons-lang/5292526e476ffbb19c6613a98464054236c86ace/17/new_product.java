public static String getMessage(final Throwable th) {
        if (th == null) {
            return StringUtils.EMPTY;
        }
        final String clsName = ClassUtils.getShortClassName(th, null);
        final String msg = th.getMessage();
        return clsName + ": " + StringUtils.defaultString(msg);
    }