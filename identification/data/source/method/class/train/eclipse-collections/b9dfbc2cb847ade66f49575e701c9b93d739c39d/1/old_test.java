    @Test
    public void set()
    {
        Assert.assertEquals("1", this.list.set(0, "2"));
        Assert.assertEquals(FastList.newListWith("2"), this.list);
        Verify.assertThrows(IndexOutOfBoundsException.class, () -> this.list.set(1, "2"));
    }