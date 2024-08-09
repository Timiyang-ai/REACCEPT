@SneakyThrows(StreamSegmentException.class)
    public void append(SegmentHandle handle, InputStream data, int length) {
        ensurePreconditions();
        Preconditions.checkArgument(!handle.isReadOnly(), "Cannot append using a read-only handle.");
        getStreamSegmentData(handle.getSegmentName()).append(data, length);
    }