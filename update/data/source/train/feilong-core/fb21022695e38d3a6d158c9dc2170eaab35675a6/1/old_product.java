public static void copyProperties(Object toObj,Object fromObj,String...includePropertyNames){
        Validate.notNull(toObj, "toObj [destination bean] not specified!");
        Validate.notNull(fromObj, "fromObj [origin bean] not specified!");

        //---------------------------------------------------------------

        if (isNullOrEmpty(includePropertyNames)){
            try{
                BeanUtils.copyProperties(toObj, fromObj);
                return;
            }catch (Exception e){
                String pattern = "copyProperties exception,message:[{}],toObj:[{}],fromObj:[{}],includePropertyNames:[{}]";
                String message = Slf4jUtil.format(pattern, e.getMessage(), toObj, fromObj, includePropertyNames);
                throw new BeanOperationException(message, e);
            }
        }

        //---------------------------------------------------------------
        for (String propertyName : includePropertyNames){
            String value = getProperty(fromObj, propertyName);
            setProperty(toObj, propertyName, value);
        }
    }