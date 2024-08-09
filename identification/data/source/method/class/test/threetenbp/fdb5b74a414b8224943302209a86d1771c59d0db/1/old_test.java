@Test(groups={"tck"})
    public void test_next_serialization() throws IOException, ClassNotFoundException {
        DateTimeAdjuster next = DateTimeAdjusters.next(SUNDAY);
        assertTrue(next instanceof Serializable);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(next);
        oos.close();

        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(
                baos.toByteArray()));
        assertEquals(ois.readObject(), next);
    }