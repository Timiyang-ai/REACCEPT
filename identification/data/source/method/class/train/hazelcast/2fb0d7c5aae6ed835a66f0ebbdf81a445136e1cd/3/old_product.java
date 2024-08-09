public void init(SerializationService serializationService, Data key, Object value) {
        if (key == null) {
            throw new IllegalArgumentException("keyData cannot be null");
        }

        this.serializationService = serializationService;

        this.key = key;
        this.value = value;
    }