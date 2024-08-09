public static byte[] makeRowKey(String applicationName, short applicationType, long timestamp) {
        if (applicationName == null) {
            throw new NullPointerException("applicationName must not be null");
        }
        final byte[] applicationNameBytes= BytesUtils.toBytes(applicationName);

        final Buffer buffer = new AutomaticBuffer(2 + applicationNameBytes.length + 2 + 8);
//        buffer.put2PrefixedString(applicationName);
        buffer.putShort((short)applicationNameBytes.length);
        buffer.putBytes(applicationNameBytes);
        buffer.putShort(applicationType);
        long reverseTimeMillis = TimeUtils.reverseTimeMillis(timestamp);
        buffer.putLong(reverseTimeMillis);
        return buffer.getBuffer();
    }