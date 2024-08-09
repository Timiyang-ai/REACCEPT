@Test(groups={"tck"})
    public void test_firstDayOfYear_serialization() throws IOException, ClassNotFoundException {
        DateTimeAdjuster firstDayOfYear = DateTimeAdjusters.firstDayOfYear();
        assertTrue(firstDayOfYear instanceof Serializable);
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(firstDayOfYear);
        oos.close();
        
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray()));
        assertSame(ois.readObject(), firstDayOfYear);
    }