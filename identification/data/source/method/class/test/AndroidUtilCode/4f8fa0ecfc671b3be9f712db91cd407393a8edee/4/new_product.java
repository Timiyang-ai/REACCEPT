public static byte[] encryptMD2(final byte[] data) {
        return hashTemplate(data, "MD2");
    }