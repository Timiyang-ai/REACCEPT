@Override
    public Double geoDist(String key, String member1, String member2, GeoUnit geoUnit) {
        Double result = delegate.geoDist(serialize(key), serialize(member1), serialize(member2), geoUnit);
        if (isFutureConversion()){
            addResultConverter(identityConverter);
        }
        return result;
    }