public static String decode(String value,String charsetType){
        if (Validator.isNullOrEmpty(value)){
            return StringUtils.EMPTY;
        }
        if (Validator.isNullOrEmpty(charsetType)){
            return value;
        }
        try{
            return URLDecoder.decode(value, charsetType);
        }catch (UnsupportedEncodingException e){
            LOGGER.error("UnsupportedEncodingException:", e);
            throw new URIParseException(e);
        }
    }