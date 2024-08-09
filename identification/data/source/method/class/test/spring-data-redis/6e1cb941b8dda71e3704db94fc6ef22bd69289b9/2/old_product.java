@Override
    public Long geoAdd(String key, Map<String, GeoCoordinate> memberCoordinateMap) {
        Map<byte[], GeoCoordinate> byteMap = new HashMap<byte[], GeoCoordinate>();
        for (String k : memberCoordinateMap.keySet()){
            byteMap.put(serialize(k), memberCoordinateMap.get(k));
        }
        Long result = delegate.geoAdd(serialize(key), byteMap);
        if (isFutureConversion()){
            addResultConverter(identityConverter);
        }
        return result;
    }