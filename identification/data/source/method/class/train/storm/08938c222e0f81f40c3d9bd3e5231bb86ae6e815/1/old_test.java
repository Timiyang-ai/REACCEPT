    @Test
    public void selectTest() {
        Fields fields = new Fields("foo", "bar");
        List<Object> second = Arrays.asList(new Object[]{ "b" });
        List<Object> tuple = Arrays.asList(new Object[]{ "a", "b", "c" });
        List<Object> pickSecond = fields.select(new Fields("bar"), tuple);
        Assert.assertTrue(pickSecond.equals(second));

        List<Object> secondAndFirst = Arrays.asList(new Object[]{ "b", "a" });
        List<Object> pickSecondAndFirst = fields.select(new Fields("bar", "foo"), tuple);
        Assert.assertTrue(pickSecondAndFirst.equals(secondAndFirst));
    }