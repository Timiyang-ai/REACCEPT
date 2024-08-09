public static String getMd5(ConfigPayload payload) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            payload.serialize(baos, new JsonFormat(true));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        MessageDigest md5 = getMd5Instance();
        md5.update(baos.toByteArray());
        return HexDump.toHexString(md5.digest()).toLowerCase();
    }