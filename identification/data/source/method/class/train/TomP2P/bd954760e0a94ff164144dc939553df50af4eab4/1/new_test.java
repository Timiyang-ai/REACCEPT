@Test
    public void testEncodeDecode() throws Exception {
        // encode
        Message m1 = Utils2.createDummyMessage();
        Message m2 = encodeDecode(m1);
        compareMessage(m1, m2);
    }