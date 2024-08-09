public static String getMd5(ConfigPayload payload) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            payload.serialize(baos, new JsonFormat(true));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return getMd5(baos.toByteArray());
    }