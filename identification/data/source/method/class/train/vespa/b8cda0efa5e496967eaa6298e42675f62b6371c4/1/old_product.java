public static String getMd5(String input) {
        MessageDigest md5 = getMd5Instance();
        md5.update(IOUtils.utf8ByteBuffer(input));
        return HexDump.toHexString(md5.digest()).toLowerCase();
    }