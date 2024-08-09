@Test(groups={"tck"})
    public void test_lastDayOfMonth_serialization() throws IOException, ClassNotFoundException {
        WithAdjuster lastDayOfMonth = DateTimeAdjusters.lastDayOfMonth();
        assertTrue(lastDayOfMonth instanceof Serializable);
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(lastDayOfMonth);
        oos.close();
        
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray()));
        assertSame(ois.readObject(), lastDayOfMonth);
    }