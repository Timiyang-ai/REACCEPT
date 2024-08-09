@Deprecated
    public static String toHexStringUpperCase(String original,String charsetName){
        String hexStringUpperCase = ByteUtil.bytesToHexStringUpperCase(getBytes(original, charsetName));
        LOGGER.debug("original:[{}],hexStringUpperCase:[{}]", original, hexStringUpperCase);
        return hexStringUpperCase;
    }