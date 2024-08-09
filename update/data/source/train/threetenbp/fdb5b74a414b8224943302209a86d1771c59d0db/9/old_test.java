@Test(groups={"tck"})
    public void test_previous_serialization() throws IOException, ClassNotFoundException {
        DateTimeAdjuster previous = DateTimeAdjusters.previous(SUNDAY);
        assertTrue(previous instanceof Serializable);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(previous);
        oos.close();

        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(
                baos.toByteArray()));
        assertEquals(ois.readObject(), previous);
    }