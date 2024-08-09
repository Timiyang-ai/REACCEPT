@Test(dataProvider = "calendarsystemtype")
    public void test_chronoSerializationSingleton(Chronology chrono, String calendarType) throws Exception {
        Chronology orginal = chrono;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(baos);
        out.writeObject(orginal);
        out.close();
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream in = new ObjectInputStream(bais);
        Chronology ser = (Chronology) in.readObject();
        assertSame(ser, chrono, "Deserialized Chrono is not the singleton serialized");
    }