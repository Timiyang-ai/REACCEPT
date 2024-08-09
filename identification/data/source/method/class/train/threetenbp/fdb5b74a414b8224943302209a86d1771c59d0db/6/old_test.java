@Test(groups={"tck"})
    public void test_lastDayOfYear_serialization() throws IOException, ClassNotFoundException {
        DateTimeAdjuster lastDayOfYear = DateTimeAdjusters.lastDayOfYear();
        assertTrue(lastDayOfYear instanceof Serializable);
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(lastDayOfYear);
        oos.close();
        
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray()));
        assertSame(ois.readObject(), lastDayOfYear);
    }