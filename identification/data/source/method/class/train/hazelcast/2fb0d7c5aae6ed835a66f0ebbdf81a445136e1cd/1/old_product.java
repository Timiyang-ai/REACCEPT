public void init(SerializationService serializationService, Data indexKey, Object key, Object value) {
        if (indexKey == null) {
            throw new IllegalArgumentException("index keyData cannot be null");
        }
        if (key == null) {
            throw new IllegalArgumentException("keyData cannot be null");
        }

        this.indexKey = indexKey;
        this.serializationService = serializationService;

        this.key = key;
        this.value = value;
    }