public static Map<String, Object> describe(Object bean){
        Validate.notNull(bean, "bean can't be null!");
        try{
            return PropertyUtils.describe(bean);
        }catch (Exception e){
            throw new BeanUtilException(e);
        }
    }