@SuppressWarnings("unchecked")
    public static <T> T getStaticFieldValue(String className,String fieldName){
        try{
            Class<?> ownerClass = ClassUtil.loadClass(className);
            // Field field = org.apache.commons.lang3.reflect.FieldUtils.getField(ownerClass, fieldName);
            Field field = ownerClass.getField(fieldName);
            return (T) field.get(ownerClass);
        }catch (Exception e){
            String formatMessage = Slf4jUtil.formatMessage("className:[{}],fieldName:[{}]", className, fieldName);
            LOGGER.error(formatMessage + e.getClass().getName(), e);
            throw new ReflectException(formatMessage, e);
        }
    }