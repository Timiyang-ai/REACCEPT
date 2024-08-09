@Test
    public void testSetUser() {
        Assert.assertNull(this.a.getUser());
        this.a.setUser(USER);
        Assert.assertEquals(USER, this.a.getUser());
    }