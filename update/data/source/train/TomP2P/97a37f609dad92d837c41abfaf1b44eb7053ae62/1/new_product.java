private Message encodeDecode(final Message m1, final byte[] privateKeySender, final byte[] privateKeyRecipient, int bufferSize)
            throws GeneralSecurityException, IOException {
        ByteBuffer buffer = ByteBuffer.wrap(new byte[bufferSize]);
        Codec.encode(buffer, m1, create(m1.sender(), privateKeySender), true);
        Message m2 = new Message();
        buffer.flip();
        Codec.decode(buffer, m2, create(m1.recipient(), privateKeyRecipient), m1.recipient().createSocket(m1.sender()), m1.sender().createSocket(m1.recipient()));
        return m2;
    }