@Test(groups={"tck"})
    public void test_nextOrCurrent_serialization() throws IOException, ClassNotFoundException {
        DateTimeAdjuster nextOrCurrent = DateTimeAdjusters.nextOrCurrent(SUNDAY);
        assertTrue(nextOrCurrent instanceof Serializable);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(nextOrCurrent);
        oos.close();

        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(
                baos.toByteArray()));
        assertEquals(ois.readObject(), nextOrCurrent);
    }