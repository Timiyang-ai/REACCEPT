@SuppressWarnings("unchecked")
    @Override
    public synchronized T get(MarshallingContext context) throws IOException, ClassNotFoundException {
        if (this.object == null) {
            this.context = context;
            if (this.bytes != null) {
                Unmarshaller unmarshaller = context.createUnmarshaller();
                try {
                    unmarshaller.start(Marshalling.createByteInput(new ByteArrayInputStream(this.bytes)));
                    this.object = (T) unmarshaller.readObject();
                    unmarshaller.finish();
                    this.bytes = null; // Free up memory
                } finally {
                    unmarshaller.close();
                }
            }
        }
        return this.object;
    }