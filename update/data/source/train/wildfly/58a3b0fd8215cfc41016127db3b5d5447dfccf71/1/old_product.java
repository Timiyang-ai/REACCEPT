@SuppressWarnings("unchecked")
    @Override
    public synchronized T get(MarshallingContext context) throws IOException, ClassNotFoundException {
        if (this.object == null) {
            this.context = context;
            if (this.bytes != null) {
                ByteArrayInputStream input = new ByteArrayInputStream(this.bytes);
                ClassLoader loader = setThreadContextClassLoader(this.context.getClassLoader());
                try (SimpleDataInput data = new SimpleDataInput(Marshalling.createByteInput(input))) {
                    int version = IndexSerializer.VARIABLE.readInt(data);
                    try (Unmarshaller unmarshaller = context.createUnmarshaller(version)) {
                        unmarshaller.start(data);
                        this.object = (T) unmarshaller.readObject();
                        unmarshaller.finish();
                        this.bytes = null; // Free up memory
                    }
                } finally {
                    setThreadContextClassLoader(loader);
                }
            }
        }
        return this.object;
    }