@Override
    public Long geoAdd(byte[] key, Map<byte[], GeoCoordinate> memberCoordinateMap) {
        Long result = delegate.geoAdd(key, memberCoordinateMap);
        if (isFutureConversion()){
            addResultConverter(identityConverter);
        }
        return result;
    }