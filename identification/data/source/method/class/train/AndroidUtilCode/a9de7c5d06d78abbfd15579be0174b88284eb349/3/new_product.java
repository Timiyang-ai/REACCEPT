public byte[] getBytes(@NonNull final String key, final byte[] defaultValue) {
        return realGetBytes(TYPE_BYTE + key, defaultValue);
    }