static byte[] parseBytesOrNull(final String deviceMessageAsHex) {
        if (deviceMessageAsHex == null) {
            return null;
        } else {
            return DatatypeConverter.parseHexBinary(deviceMessageAsHex);
        }
    }