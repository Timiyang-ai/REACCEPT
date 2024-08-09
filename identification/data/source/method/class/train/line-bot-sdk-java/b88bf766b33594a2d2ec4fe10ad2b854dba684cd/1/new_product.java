static byte[] parseBytesOrNull(final String deviceMessageAsHex) {
        if (deviceMessageAsHex == null) {
            return null;
        }

        final int length = deviceMessageAsHex.length();
        final int resultSize = length / 2;

        if (length % 2 != 0) {
            throw new IllegalArgumentException("hex string needs to be even-length: " + deviceMessageAsHex);
        }

        final byte[] bytes = new byte[resultSize];

        for (int pos = 0; pos < resultSize; pos++) {
            bytes[pos] = (byte) Integer.parseInt(deviceMessageAsHex.substring(pos * 2, pos * 2 + 2), 16);
            // Byte.parseByte doesn't support >= 0x80.
        }

        return bytes;
    }