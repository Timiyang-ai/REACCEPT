public static <T> T populate(T bean,Map<String, ?> properties){
        Validate.notNull(bean, "bean can't be null/empty!");
        Validate.notNull(properties, "properties can't be null/empty!");

        try{
            BeanUtils.populate(bean, properties);
            return bean;
        }catch (Exception e){
            throw new BeanUtilException(e);
        }
    }