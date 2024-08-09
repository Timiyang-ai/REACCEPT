@Test
    public void testEncodeDecode() throws Exception {
        // encode
        Message2 m1 = Utils2.createDummyMessage();
        Message2 m2 = encodeDecode(m1);
        compareMessage(m1, m2);
    }