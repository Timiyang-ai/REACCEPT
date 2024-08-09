public static UUID nameUUIDFromBytes(byte[] name) {
        if (name == null) {
            throw new NullPointerException();
        }

        byte[] hash;
        // TODO(tball): uncomment when MD5 hashing is supported.
//        try {
//            MessageDigest md = MessageDigest.getInstance("MD5"); //$NON-NLS-1$
//            hash = md.digest(name);
//        } catch (NoSuchAlgorithmException e) {
//            throw new AssertionError(e);
//        }
        hash = md5(name);

        long msb = (hash[0] & 0xFFL) << 56;
        msb |= (hash[1] & 0xFFL) << 48;
        msb |= (hash[2] & 0xFFL) << 40;
        msb |= (hash[3] & 0xFFL) << 32;
        msb |= (hash[4] & 0xFFL) << 24;
        msb |= (hash[5] & 0xFFL) << 16;
        msb |= (hash[6] & 0x0FL) << 8;
        msb |= (0x3L << 12); // set the version to 3
        msb |= (hash[7] & 0xFFL);

        long lsb = (hash[8] & 0x3FL) << 56;
        lsb |= (0x2L << 62); // set the variant to bits 01
        lsb |= (hash[9] & 0xFFL) << 48;
        lsb |= (hash[10] & 0xFFL) << 40;
        lsb |= (hash[11] & 0xFFL) << 32;
        lsb |= (hash[12] & 0xFFL) << 24;
        lsb |= (hash[13] & 0xFFL) << 16;
        lsb |= (hash[14] & 0xFFL) << 8;
        lsb |= (hash[15] & 0xFFL);
        return new UUID(msb, lsb);
    }