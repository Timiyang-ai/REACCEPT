@Test(groups={"tck"})
    public void test_previousOrCurrent_serialization() throws IOException, ClassNotFoundException {
        DateTimeAdjuster previousOrCurrent = DateTimeAdjusters.previousOrCurrent(SUNDAY);
        assertTrue(previousOrCurrent instanceof Serializable);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(previousOrCurrent);
        oos.close();

        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(
                baos.toByteArray()));
        assertEquals(ois.readObject(), previousOrCurrent);
    }