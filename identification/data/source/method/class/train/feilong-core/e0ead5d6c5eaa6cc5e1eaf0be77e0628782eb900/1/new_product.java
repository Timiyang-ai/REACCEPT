public static Map<String, Object> describe(Object bean,String...propertyNames){
        Validate.notNull(bean, "bean can't be null!");
        if (isNullOrEmpty(propertyNames)){
            try{
                return PropertyUtils.describe(bean);
            }catch (Exception e){
                throw new BeanUtilException(e);
            }
        }
        Map<String, Object> map = newLinkedHashMap(propertyNames.length);
        for (String propertyName : propertyNames){
            map.put(propertyName, getProperty(bean, propertyName));
        }
        return map;
    }