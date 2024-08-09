public static void copyProperties(Object toObj,Object fromObj,String...includePropertyNames){
        Validate.notNull(toObj, "toObj [destination bean] not specified!");
        Validate.notNull(fromObj, "fromObj [origin bean] not specified!");

        if (isNullOrEmpty(includePropertyNames)){
            try{
                BeanUtils.copyProperties(toObj, fromObj);
                return;
            }catch (Exception e){
                LOGGER.error(e.getClass().getName(), e);
                throw new BeanOperationException(e);
            }
        }
        for (String propertyName : includePropertyNames){
            String value = getProperty(fromObj, propertyName);
            setProperty(toObj, propertyName, value);
        }
    }