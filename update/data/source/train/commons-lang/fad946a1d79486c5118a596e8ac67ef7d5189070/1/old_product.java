public static void printRootCauseStackTrace(final Throwable throwable, final PrintWriter writer) {
        if (throwable == null) {
            return;
        }
        if (writer == null) {
            throw new IllegalArgumentException("The PrintWriter must not be null");
        }
        String trace[] = getRootCauseStackTrace(throwable);
        for (String element : trace) {
            writer.println(element);
        }
        writer.flush();
    }