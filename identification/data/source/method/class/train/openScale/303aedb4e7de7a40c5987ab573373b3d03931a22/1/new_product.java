public static double getBase10Float(byte[] data, int offset) {
        int mantissa = Converters.fromUnsignedInt24Le(data, offset);
        int exponent = data[offset + 3];  // note: byte is signed.
        return mantissa * Math.pow(10, exponent);
    }