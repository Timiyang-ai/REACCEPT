@Test
    public void testSetName() {
        final Application a = new Application();
        Assert.assertNull(a.getName());
        a.setName(NAME);
        Assert.assertEquals(NAME, a.getName());
    }