public static String getMd5(byte[] input) {
        MessageDigest md5 = getMd5Instance();
        md5.update(input);
        return HexDump.toHexString(md5.digest()).toLowerCase();
    }