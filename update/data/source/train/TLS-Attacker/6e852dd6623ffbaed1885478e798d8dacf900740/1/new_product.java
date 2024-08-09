@Override
    public void adjustTLSContext(PaddingExtensionMessage message) {
        if (message.getPaddingBytes().getValue().length <= 65535) {
            LOGGER.warn("The Padding Extension length value exceeds the two bytes defined in RFC 7685.");
        }
        context.setPaddingExtensionLength(message.getPaddingBytes().getValue().length);

    }