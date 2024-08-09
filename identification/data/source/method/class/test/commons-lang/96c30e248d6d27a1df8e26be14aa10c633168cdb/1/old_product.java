public static byte[] nextBytes(int count) {
        Validate.isTrue(count >= 0, "Count cannot be negative.");

        byte[] result = new byte[count];
        RANDOM.nextBytes(result);
        return result;
    }