@SuppressWarnings("unchecked")
    @Override
    public synchronized T get(MarshallingContext context) throws IOException, ClassNotFoundException {
        if (this.object == null) {
            this.context = context;
            if (this.buffer != null) {
                ByteArrayInputStream input = new ByteArrayInputStream(this.buffer.array(), this.buffer.arrayOffset(), this.buffer.limit() - this.buffer.arrayOffset());
                ClassLoader loader = setThreadContextClassLoader(this.context.getClassLoader());
                try (SimpleDataInput data = new SimpleDataInput(Marshalling.createByteInput(input))) {
                    int version = IndexSerializer.UNSIGNED_BYTE.readInt(data);
                    try (Unmarshaller unmarshaller = context.createUnmarshaller(version)) {
                        unmarshaller.start(data);
                        this.object = (T) unmarshaller.readObject();
                        unmarshaller.finish();
                        this.buffer = null; // Free up memory
                    }
                } finally {
                    setThreadContextClassLoader(loader);
                }
            }
        }
        return this.object;
    }