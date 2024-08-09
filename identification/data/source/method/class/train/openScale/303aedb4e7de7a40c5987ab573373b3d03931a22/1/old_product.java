public static double getBase10Float(byte[] data, int offset) {
        int mantissa = (data[offset] & 0xff) | ((data[offset + 1] & 0xff) << 8) |
                ((data[offset + 2] & 0xff) << 16);
        int exponent = data[offset + 3];  // note: byte is signed.
        return mantissa * Math.pow(10, exponent);
    }