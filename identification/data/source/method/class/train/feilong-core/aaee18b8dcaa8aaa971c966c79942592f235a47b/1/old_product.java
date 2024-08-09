public static void populate(Object bean,Map<String, ?> properties){
        Validate.notNull(bean, "bean can't be null/empty!");
        Validate.notNull(properties, "properties can't be null/empty!");

        try{
            BeanUtils.populate(bean, properties);
        }catch (Exception e){
            throw new BeanUtilException(e);
        }
    }