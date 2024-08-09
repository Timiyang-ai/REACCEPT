@Override
    public int parseExtension(byte[] message, int pointer) {
        SignatureAndHashAlgorithmsExtensionMessage extension;
        if (extensionMessage == null) {
            extension = new SignatureAndHashAlgorithmsExtensionMessage();
        } else {
            extension = (SignatureAndHashAlgorithmsExtensionMessage) extensionMessage;
        }
        // check if correct extension is passed
        if (message[pointer] != (byte) 0 && message[pointer + 1] != (byte) 13) {
            throw new IllegalArgumentException(
                    "Extension isn't a SignatureAndHashAlgorithms Extension. First Bytes should be '0' and '13'");
        }
        // set extension type
        extension.setExtensionType(ExtensionType.SIGNATURE_AND_HASH_ALGORITHMS.getValue());
        int newPointer = pointer + ExtensionByteLength.TYPE;

        // set extension and signature and hash algorithm extension length
        extension.setExtensionLength(ArrayConverter.bytesToInt(new byte[] { message[newPointer],
                message[newPointer + 1] }));
        newPointer += ExtensionByteLength.EXTENSIONS;

        extension.setSignatureAndHashAlgorithmsLength(ArrayConverter.bytesToInt(new byte[] { message[newPointer],
                message[newPointer + 1] }));
        newPointer += SIGNATURE_AND_HASH_ALGORITHMS_LENGTH;

        // create the SignatureAndHashAlgorithmsConfig (List) and the byte
        // values of them
        int pairingsCount = extension.getSignatureAndHashAlgorithmsLength().getValue() / 2;
        ArrayList<SignatureAndHashAlgorithm> signatureAndHashConfig = new ArrayList<>();
        ByteArrayOutputStream signatureAndHashBytes = new ByteArrayOutputStream();

        for (int i = newPointer; i <= newPointer + pairingsCount; i += 2) {
            signatureAndHashConfig.add(new SignatureAndHashAlgorithm(new byte[] { message[i], message[i + 1] }));
            signatureAndHashBytes.write(message, i, 2);
        }
        extension.setSignatureAndHashAlgorithms(signatureAndHashBytes.toByteArray());
        // the extension bytes are exactly the same, than in the message. Thus
        // we copy them.
        newPointer += extension.getSignatureAndHashAlgorithmsLength().getValue();
        extension.setExtensionBytes(Arrays.copyOfRange(message, pointer, newPointer));
        extensionMessage = extension;
        return newPointer;
    }