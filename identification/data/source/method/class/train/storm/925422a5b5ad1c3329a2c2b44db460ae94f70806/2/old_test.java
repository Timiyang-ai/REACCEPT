    @Test
    public void containsTest() {
        Fields fields = new Fields("foo", "bar");
        Assert.assertTrue(fields.contains("foo"));
        Assert.assertTrue(fields.contains("bar"));
        Assert.assertFalse(fields.contains("baz"));
    }