@TestTargetNew(
        level = TestLevel.COMPLETE,
        notes = "",
        method = "!Serialization",
        args = {}
    )
    public void testReadObject() throws Exception {
        String secret = "secret string";
        SealedObject so = new SealedObject(secret, new NullCipher());
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(so);

        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(
                bos.toByteArray()));

        SealedObject so_des = (SealedObject) ois.readObject();
        assertEquals("The secret content of deserialized object "
                + "should be equal to the secret content of initial object",
                secret, so_des.getObject(new NullCipher()));
        assertEquals("The value returned by getAlgorithm() method of "
                + "deserialized object should be equal to the value returned "
                + "by getAlgorithm() method of initial object", so
                .getAlgorithm(), so_des.getAlgorithm());
    }