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
        if (cipherSuite == CipherSuite.TLS_FALLBACK_SCSV || cipherSuite == CipherSuite.TLS_EMPTY_RENEGOTIATION_INFO_SCSV || cipherSuite == CipherSuite.TLS_UNKNOWN_CIPHER) {
            throw new IllegalArgumentException("The CipherSuite:" + cipherSuite.name() + " does not specify a CipherType");
        }
        throw new UnsupportedOperationException("Cipher suite " + cipherSuite + " is not supported yet.");
    }