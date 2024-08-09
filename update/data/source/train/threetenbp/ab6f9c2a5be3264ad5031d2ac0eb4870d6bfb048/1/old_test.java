@Test(dataProvider = "calendarsystemtype")
    public <C extends Chronology<C>> void test_chronoSerializationSingleton(C chrono, String calendarType) throws Exception {
        C orginal = chrono;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(baos);
        out.writeObject(orginal);
        out.close();
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream in = new ObjectInputStream(bais);
        C ser = (C)in.readObject();
        assertSame(ser, chrono, "Deserialized Chrono is not the singleton serialized");
    }