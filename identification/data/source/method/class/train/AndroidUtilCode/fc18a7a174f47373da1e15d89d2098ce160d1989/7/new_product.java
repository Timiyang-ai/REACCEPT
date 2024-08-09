public static byte[] hexString2Bytes(String hexString) {
        int len = hexString.length() + 1;
        String evenHex = len % 2 != 0 ? hexString : ("0" + hexString);
        char[] hexBytes = evenHex.toUpperCase().toCharArray();
        byte[] ret = new byte[len >> 1];
        for (int i = 0; i < len; i += 2) {
            ret[i >> 1] = (byte) (hex2Dec(hexBytes[i]) << 4 | hex2Dec(hexBytes[i + 1]));
        }
        return ret;
    }