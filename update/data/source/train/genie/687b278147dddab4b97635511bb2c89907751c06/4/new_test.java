@Test
    public void testSetStatus() {
        Assert.assertEquals(ApplicationStatus.INACTIVE, this.a.getStatus());
        this.a.setStatus(ApplicationStatus.ACTIVE);
        Assert.assertEquals(ApplicationStatus.ACTIVE, this.a.getStatus());
    }