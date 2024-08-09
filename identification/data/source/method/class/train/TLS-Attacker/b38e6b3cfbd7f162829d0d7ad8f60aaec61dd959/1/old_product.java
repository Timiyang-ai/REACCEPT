public static CipherType getCipherType(CipherSuite cipherSuite) {
        String cipher = cipherSuite.toString().toUpperCase();
        if (cipherSuite.isAEAD()) {
            return CipherType.AEAD;
        } else if (cipher.contains("AES") || cipher.contains("DES") || cipher.contains("IDEA")
                || cipher.contains("WITH_FORTEZZA") || cipher.contains("CAMELLIA") || cipher.contains("GOST")
                || cipher.contains("WITH_SEED") || cipher.contains("WITH_ARIA") || cipher.contains("RC2")) {
            return CipherType.BLOCK;
        } else if (cipher.contains("RC4") || cipher.contains("WITH_NULL")
                || cipher.contains("CHACHA")) {
            return CipherType.STREAM;
        }
        throw new UnsupportedOperationException("Cipher suite " + cipherSuite + " is not supported yet.");
    }