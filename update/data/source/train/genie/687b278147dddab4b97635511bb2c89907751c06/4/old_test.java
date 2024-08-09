@Test
    public void testSetStatus() {
        final Application a = new Application();
        Assert.assertEquals(ApplicationStatus.INACTIVE, a.getStatus());
        a.setStatus(ApplicationStatus.ACTIVE);
        Assert.assertEquals(ApplicationStatus.ACTIVE, a.getStatus());
    }