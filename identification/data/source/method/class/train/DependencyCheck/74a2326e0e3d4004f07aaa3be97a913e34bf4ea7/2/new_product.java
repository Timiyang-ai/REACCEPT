public void mergeProperties(InputStream stream) throws IOException {
        props.load(stream);
        logProperties("Properties updated via merge", props);
    }