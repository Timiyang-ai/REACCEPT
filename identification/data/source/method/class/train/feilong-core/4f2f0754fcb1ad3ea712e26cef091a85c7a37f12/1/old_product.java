@SuppressWarnings("unchecked")
    public static <T> T getStaticProperty(String className,String fieldName){
        try{
            Class<?> ownerClass = ClassUtil.loadClass(className);
            Field field = ownerClass.getField(fieldName);
            return (T) field.get(ownerClass);
        }catch (Exception e){
            LOGGER.error(e.getClass().getName(), e);
            throw new ReflectException(e);
        }
    }