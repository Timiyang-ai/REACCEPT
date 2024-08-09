@Test
    public void testSetName() {
        Assert.assertNull(this.a.getName());
        this.a.setName(NAME);
        Assert.assertEquals(NAME, this.a.getName());
    }