public static <T, O> Map<T, O> groupOne(Collection<O> objectCollection,String propertyName){
        if (Validator.isNullOrEmpty(objectCollection)){
            return Collections.emptyMap();
        }
        Validate.notBlank(propertyName, "propertyName can't be null/empty!");

        Map<T, O> map = MapUtil.newLinkedHashMap(objectCollection.size());
        for (O o : objectCollection){
            T key = PropertyUtil.getProperty(o, propertyName);
            if (!map.containsKey(key)){
                map.put(key, o);
            }else{
                if (LOGGER.isDebugEnabled()){
                    LOGGER.debug("map:{} already has the key:{},ignore!", JsonUtil.format(map.keySet()), key);
                }
            }
        }
        return map;
    }