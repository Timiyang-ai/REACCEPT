public static byte[] convertCharsToBytes(char[] chars, String encoding) throws UnsupportedEncodingException {
        byte[] result = new byte[chars.length*2];
        for (int i=0; i<chars.length;i++) {
            result[2*i] = (byte) (chars[i] / 256);
            result[2*i+1] = (byte) (chars[i] % 256);
        }
        return result;
    }