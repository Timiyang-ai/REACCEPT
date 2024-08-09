private Class<?> usePublicApiClass(Class<?> type) {
        if ("DirectByteBuffer".equals(type.getSimpleName()))
            return ByteBuffer.class;

        if (ChannelReceiver.class.isAssignableFrom(type))
            return ChannelReceiver.class;

        return type;
    }