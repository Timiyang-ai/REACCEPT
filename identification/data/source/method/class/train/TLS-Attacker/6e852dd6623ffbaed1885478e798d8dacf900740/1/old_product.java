@Override
    public void adjustTLSContext(PaddingExtensionMessage message) {
        if (message.getPaddingLength().getValue() <= 65535) {
            context.setPaddingExtensionLength(message.getPaddingLength().getValue());
        } else {
            LOGGER.warn("The Padding Extension length value exceeds the two bytes defined in RFC 7685.");
            context.setPaddingExtensionLength(message.getPaddingLength().getValue());
        }
    }