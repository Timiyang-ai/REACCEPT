    @Test
    public void fieldIndexTest() {
        Fields fields = new Fields("foo", "bar");
        Assert.assertEquals(fields.fieldIndex("foo"), 0);
        Assert.assertEquals(fields.fieldIndex("bar"), 1);
    }