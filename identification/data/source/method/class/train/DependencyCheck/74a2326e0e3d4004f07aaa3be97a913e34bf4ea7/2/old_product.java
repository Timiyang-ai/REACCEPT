public static void mergeProperties(InputStream stream) throws IOException {
        LOCAL_SETTINGS.get().props.load(stream);
        logProperties("Properties updated via merge", LOCAL_SETTINGS.get().props);
    }