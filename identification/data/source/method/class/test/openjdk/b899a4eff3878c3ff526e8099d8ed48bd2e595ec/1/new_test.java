@Test( groups={"tck"}, dataProvider="calendars")
    public void test_ChronoLocalDateTimeSerialization(Chronology chrono) throws Exception {
        LocalDateTime ref = LocalDate.of(2000, 1, 5).atTime(12, 1, 2, 3);
        ChronoLocalDateTime<?> orginal = chrono.date(ref).atTime(ref.toLocalTime());
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(baos);
        out.writeObject(orginal);
        out.close();
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream in = new ObjectInputStream(bais);
        @SuppressWarnings("unchecked")
        ChronoLocalDateTime<?> ser = (ChronoLocalDateTime<?>) in.readObject();
        assertEquals(ser, orginal, "deserialized date is wrong");
    }