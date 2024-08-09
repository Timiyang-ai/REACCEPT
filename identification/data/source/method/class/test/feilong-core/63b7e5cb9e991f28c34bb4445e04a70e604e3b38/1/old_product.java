@Deprecated
    public static String toOriginal(String hexStringUpperCase,String charsetName){
        byte[] hexBytesToBytes = ByteUtil.hexBytesToBytes(hexStringUpperCase.getBytes());
        String original = newString(hexBytesToBytes, charsetName);
        LOGGER.debug("hexStringUpperCase:{},original:{}", hexStringUpperCase, original);
        return original;
    }