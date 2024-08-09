public static List<Pkcs1Vector> generatePlainPkcs1Vectors(int publicKeyBitLength,
            BleichenbacherCommandConfig.Type type, ProtocolVersion protocolVersion) {
        byte[] keyBytes = new byte[HandshakeByteLength.PREMASTER_SECRET];
        Arrays.fill(keyBytes, (byte) 42);
        keyBytes[0] = protocolVersion.getMajor();
        keyBytes[1] = protocolVersion.getMinor();
        int publicKeyByteLength = publicKeyBitLength / 8;

        // create plain padded keys
        List<Pkcs1Vector> pkcs1Vectors = new LinkedList<>();
        pkcs1Vectors.add(new Pkcs1Vector("Correctly formatted PKCS#1 PMS message", getPaddedKey(publicKeyByteLength,
                keyBytes)));
        pkcs1Vectors.add(new Pkcs1Vector("Wrong first byte (0x00 set to 0x17)", getEK_WrongFirstByte(
                publicKeyByteLength, keyBytes)));
        pkcs1Vectors.add(new Pkcs1Vector("Wrong second byte (0x02 set to 0x17)", getEK_WrongSecondByte(
                publicKeyByteLength, keyBytes)));
        pkcs1Vectors.add(new Pkcs1Vector("Invalid TLS version in PMS", getEK_WrongTlsVersion(publicKeyByteLength,
                keyBytes)));
        pkcs1Vectors.add(new Pkcs1Vector("Correctly formatted PKCS#1 PMS message, but 1 byte shorter", getPaddedKey(
                publicKeyByteLength - 1, keyBytes)));
        pkcs1Vectors.add(new Pkcs1Vector("No 0x00 in message", getEK_NoNullByte(publicKeyByteLength, keyBytes)));
        pkcs1Vectors.add(new Pkcs1Vector("0x00 in PKCS#1 padding (first 8 bytes after 0x00 0x02)",
                getEK_NullByteInPkcsPadding(publicKeyByteLength, keyBytes)));
        pkcs1Vectors.add(new Pkcs1Vector("0x00 in some padding byte", getEK_NullByteInPadding(publicKeyByteLength,
                keyBytes)));
        pkcs1Vectors.add(new Pkcs1Vector("0x00 on the last position  (|PMS| = 0)", getEK_SymmetricKeyOfSize(
                publicKeyByteLength, keyBytes, 0)));
        pkcs1Vectors.add(new Pkcs1Vector("0x00 on the next to last position (|PMS| = 1)", getEK_SymmetricKeyOfSize(
                publicKeyByteLength, keyBytes, 1)));
        pkcs1Vectors.add(new Pkcs1Vector("Correctly formatted PKCS#1 message, (|PMS| = 47)", getPaddedKey(
                publicKeyByteLength, Arrays.copyOf(keyBytes, HandshakeByteLength.PREMASTER_SECRET - 1))));
        pkcs1Vectors.add(new Pkcs1Vector("Correctly formatted PKCS#1 message, (|PMS| = 49)", getPaddedKey(
                publicKeyByteLength, Arrays.copyOf(keyBytes, HandshakeByteLength.PREMASTER_SECRET + 1))));

        if (type == BleichenbacherCommandConfig.Type.FULL) {
            List<Pkcs1Vector> additionalVectors = getEK_DifferentPositionsOf0x00(publicKeyByteLength, keyBytes);
            for (Pkcs1Vector vector : additionalVectors) {
                pkcs1Vectors.add(vector);
            }
        }
        return pkcs1Vectors;
    }