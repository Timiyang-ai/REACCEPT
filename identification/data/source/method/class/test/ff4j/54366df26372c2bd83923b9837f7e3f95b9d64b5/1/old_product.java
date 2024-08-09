@Override
    public Feature get(String uid) {
        if (uid == null || uid.isEmpty()) {
            throw new IllegalArgumentException("Feature identifier (param#0) cannot be null nor empty");
        }
        String value = jedis.get(uid);
        if (value != null) {
            return FeatureJsonMarshaller.unMarshallFeature(value);
        }
        return null;
    }