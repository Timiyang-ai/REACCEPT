static LicenseKey encode(License.Type type, int version, byte[] content) {
        byte[] bytes = new byte[3 * Integer.BYTES + content.length];
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
        byteBuffer.putInt(type.value())
            .putInt(version)
            .putInt(content.length)
            .put(content);
        return new LicenseKey(Base64.getEncoder().encodeToString(bytes));
    }