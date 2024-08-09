@Test
    public void testEncodeDecode4() throws Exception {
        Message m1 = Utils2.createDummyMessage();
        Random rnd = new Random(42);
        m1.setType(Message.Type.DENIED);
        m1.setHintSign();

        KeyPairGenerator gen = KeyPairGenerator.getInstance("DSA");
        KeyPair pair1 = gen.generateKeyPair();
        m1.setPublicKeyAndSign(pair1);

        Map<Number640, Data> dataMap = new HashMap<Number640, Data>();
        dataMap.put(new Number640(rnd), new Data(new byte[] { 3, 4, 5 }, true, true));
        dataMap.put(new Number640(rnd), new Data(new byte[] { 4, 5, 6 }, true, true));
        dataMap.put(new Number640(rnd), new Data(new byte[] { 5, 6, 7 }, true, true));
        m1.setDataMap(new DataMap(dataMap));
        NavigableMap<Number640, Number160> keysMap = new TreeMap<Number640, Number160>();
        keysMap.put(new Number640(rnd), new Number160(rnd));
        keysMap.put(new Number640(rnd), new Number160(rnd));
        keysMap.put(new Number640(rnd), new Number160(rnd));
        m1.setKeyMap640(new KeyMap640(keysMap));
        //

        Message m2 = encodeDecode(m1);
        Assert.assertEquals(true, m2.getPublicKey() != null);
        Assert.assertEquals(false, m2.getDataMap(0) == null);
        Assert.assertEquals(false, m2.getKeyMap640(0) == null);
        compareMessage(m1, m2);
    }