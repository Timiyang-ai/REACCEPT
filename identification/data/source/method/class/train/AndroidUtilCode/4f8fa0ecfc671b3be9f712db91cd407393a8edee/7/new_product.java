public static byte[] encryptMD2(byte[] data) {
        return hashTemplate(data, "MD2");
    }