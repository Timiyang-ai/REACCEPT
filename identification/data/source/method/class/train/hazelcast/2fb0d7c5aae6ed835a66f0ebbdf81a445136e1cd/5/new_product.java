public void init(InternalSerializationService serializationService, Data key, Object value, Extractors extractors) {
        if (key == null) {
            throw new IllegalArgumentException("keyData cannot be null");
        }

        this.serializationService = serializationService;

        this.key = key;
        this.value = value;
        this.extractors = extractors;
    }