public static void copyProperties(Object toObj,Object fromObj,String...includePropertyNames){
        Validate.notNull(toObj, "toObj [destination bean] not specified!");
        Validate.notNull(fromObj, "fromObj [origin bean] not specified!");

        //---------------------------------------------------------------

        if (isNullOrEmpty(includePropertyNames)){
            try{
                BeanUtils.copyProperties(toObj, fromObj);
                return;
            }catch (Exception e){
                String pattern = "copyProperties exception,toObj:[{}],fromObj:[{}],includePropertyNames:[{}]";
                throw new BeanOperationException(Slf4jUtil.format(pattern, toObj, fromObj, includePropertyNames), e);
            }
        }

        //---------------------------------------------------------------
        for (String propertyName : includePropertyNames){
            String value = getProperty(fromObj, propertyName);
            setProperty(toObj, propertyName, value);
        }
    }