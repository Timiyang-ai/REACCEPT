@Test(groups={"tck"})
    public void test_firstDayOfMonth_serialization() throws IOException, ClassNotFoundException {
        DateTimeAdjuster firstDayOfMonth = DateTimeAdjusters.firstDayOfMonth();
        assertTrue(firstDayOfMonth instanceof Serializable);
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(firstDayOfMonth);
        oos.close();
        
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray()));
        assertSame(ois.readObject(), firstDayOfMonth);
    }