@Override
    public List<byte[]> geoHash(byte[] key, byte[]... members) {
        List<byte[]> result = delegate.geoHash(key, members);
        if (isFutureConversion()){
            addResultConverter(identityConverter);
        }
        return result;
    }