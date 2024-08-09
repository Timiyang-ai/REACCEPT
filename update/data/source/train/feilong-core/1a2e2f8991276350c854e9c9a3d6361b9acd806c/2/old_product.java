public static String encode(String value,String charsetType){
        if (Validator.isNullOrEmpty(charsetType)){
            return value;
        }
        try{
            return URLEncoder.encode(value, charsetType);
        }catch (UnsupportedEncodingException e){
            LOGGER.error("UnsupportedEncodingException:", e);
            throw new URIParseException(e);
        }
    }