@Override
    public int parseExtension(byte[] message, int pointer) {

        if (extensionMessage == null) {
            extensionMessage = new SignatureAndHashAlgorithmsExtensionMessage();
        }
        // check if correct extension is passed
        if (message[pointer] != (byte) 0 && message[pointer + 1] != (byte) 13) {
            throw new IllegalArgumentException(
                    "Extension isn't a SignatureAndHashAlgorithms Extension. First Bytes should be '0' and '13'");
        }
        // set extension type
        extensionMessage.setExtensionType(ExtensionType.SIGNATURE_AND_HASH_ALGORITHMS.getValue());
        int newPointer = pointer + ExtensionByteLength.TYPE;

        // set extension and signature and hash algorithm extension length
        extensionMessage.setExtensionLength(ArrayConverter.bytesToInt(new byte[] { message[newPointer],
                message[newPointer + 1] }));
        newPointer += ExtensionByteLength.EXTENSIONS;

        extensionMessage.setSignatureAndHashAlgorithmsLength(ArrayConverter.bytesToInt(new byte[] {
                message[newPointer], message[newPointer + 1] }));
        newPointer += SIGNATURE_AND_HASH_ALGORITHMS_LENGTH;

        // create the SignatureAndHashAlgorithmsConfig (List) and the byte
        // values of them
        int pairingsCount = extensionMessage.getSignatureAndHashAlgorithmsLength().getValue() / 2;
        ArrayList<SignatureAndHashAlgorithm> signatureAndHashConfig = new ArrayList<>();
        ByteArrayOutputStream signatureAndHashBytes = new ByteArrayOutputStream();

        for (int i = newPointer; i <= newPointer + pairingsCount; i += 2) {
            signatureAndHashConfig.add(new SignatureAndHashAlgorithm(new byte[] { message[i], message[i + 1] }));
            signatureAndHashBytes.write(message, i, 2);
        }
        extensionMessage.setSignatureAndHashAlgorithms(signatureAndHashBytes.toByteArray());
        extensionMessage.setSignatureAndHashAlgorithmsConfig(signatureAndHashConfig);

        // the extension bytes are exactly the same, than in the message. Thus
        // we copy them.
        newPointer += extensionMessage.getSignatureAndHashAlgorithmsLength().getValue();
        extensionMessage.setExtensionBytes(Arrays.copyOfRange(message, pointer, newPointer));

        return newPointer;
    }