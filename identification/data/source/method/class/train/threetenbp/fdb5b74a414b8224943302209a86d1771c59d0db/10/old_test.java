@Test(groups={"tck"})
    public void test_firstDayOfNextYear_serialization() throws IOException, ClassNotFoundException {
        DateTimeAdjuster firstDayOfMonth = DateTimeAdjusters.firstDayOfNextYear();
        assertTrue(firstDayOfMonth instanceof Serializable);
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(firstDayOfMonth);
        oos.close();
        
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray()));
        assertSame(ois.readObject(), firstDayOfMonth);
    }