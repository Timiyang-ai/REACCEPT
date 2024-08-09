    private OutputStream newLogStream(Path logFile) throws ReflectiveOperationException {
        return (OutputStream) newLogStreamMethod.invoke(null, logFile);
    }