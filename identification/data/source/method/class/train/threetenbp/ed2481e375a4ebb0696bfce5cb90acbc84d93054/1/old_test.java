@Test( dataProvider="calendars")
    public <C extends Chrono<C>> void test_ChronoLocalDateTimeSerialization(C chrono) throws Exception {
        LocalDateTime ref = LocalDate.of(2000, 1, 5).atTime(12, 1, 2, 3);
        ChronoLocalDateTime<C> orginal = chrono.date(ref).atTime(ref.getTime());
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(baos);
        out.writeObject(orginal);
        out.close();
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream in = new ObjectInputStream(bais);
        ChronoLocalDateTime<C> ser = (ChronoLocalDateTime<C>) in.readObject();
        assertEquals(ser, orginal, "deserialized date is wrong");
    }