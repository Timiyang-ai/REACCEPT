@Override
    public List<GeoCoordinate> geoPos(byte[] key, byte[]... members) {
        List<GeoCoordinate> result = delegate.geoPos(key, members);
        if (isFutureConversion()){
            addResultConverter(identityConverter);
        }
        return result;
    }