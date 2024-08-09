@Test
    public void testEncodeDecode() throws Exception {
        Triple<Message, Curve25519KeyPair, Curve25519KeyPair> p1 = Utils2.createDummyMessage();
        final int size = 100;
        Random rnd = new Random(42);
        byte[] b = new byte[size];
        rnd.nextBytes(b);

        p1.element0().payload(ByteBuffer.wrap(b));
        Message m2 = encodeDecode(p1.element0(), p1.element1().getPrivateKey(), p1.element2().getPrivateKey(), size + Codec.HEADER_SIZE_MIN);
        compareMessage(p1.element0(), m2);
        Assert.assertTrue(m2.isDone());
    }