@Test
    public void testFlattened() {
        final Object obj = new AutoKeyAndVal<DateRange>() {
            @DynamoDBFlattened(attributes={
                @DynamoDBAttribute(mappedBy="start", attributeName="DateRangeStart"),
                @DynamoDBAttribute(mappedBy="end", attributeName="DateRangeEnd")})
            public DateRange getVal() { return super.getVal(); }
            public void setVal(final DateRange val) { super.setVal(val); }
        };
        final DynamoDBMapperTableModel<Object> model = models.getTableModel((Class<Object>)obj.getClass());
        assertEquals(3, model.fields().size());
        assertEquals("DateRangeStart", model.field("DateRangeStart").name());
        assertEquals("DateRangeEnd", model.field("DateRangeEnd").name());
    }