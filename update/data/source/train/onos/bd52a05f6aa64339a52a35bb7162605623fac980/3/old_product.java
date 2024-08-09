public static String areaAddres(byte[] bytes) {
        String areaAddres = "";
        for (Byte byt : bytes) {
            String hexa = Integer.toHexString(Byte.toUnsignedInt(byt));
            if (hexa.length() % 2 != 0) {
                hexa = "0" + hexa;
            }
            areaAddres = areaAddres + hexa;
        }
        return areaAddres;
    }