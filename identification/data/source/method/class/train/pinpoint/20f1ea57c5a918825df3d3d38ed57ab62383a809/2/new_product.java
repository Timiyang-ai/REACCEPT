public static byte[] makeRowKey(String applicationName, short applicationType, long timestamp) {
        if (applicationName == null) {
            throw new NullPointerException("applicationName must not be null");
        }
        final byte[] applicationNameBytes= BytesUtils.toBytes(applicationName);

        final Buffer buffer = new AutomaticBuffer(2 + applicationNameBytes.length + 2 + 8);
//        buffer.put2PrefixedString(applicationName);
        buffer.put((short)applicationNameBytes.length);
        buffer.put(applicationNameBytes);
        buffer.put(applicationType);
        long reverseTimeMillis = TimeUtils.reverseCurrentTimeMillis(timestamp);
        buffer.put(reverseTimeMillis);
        return buffer.getBuffer();
    }