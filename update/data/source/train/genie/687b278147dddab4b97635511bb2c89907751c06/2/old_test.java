@Test
    public void testSetUser() {
        final Application a = new Application();
        Assert.assertNull(a.getUser());
        a.setUser(USER);
        Assert.assertEquals(USER, a.getUser());
    }