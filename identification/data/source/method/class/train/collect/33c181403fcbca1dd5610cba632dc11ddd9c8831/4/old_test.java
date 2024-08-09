    @Test
    public void getUploadResultMessageTest() {
        fillTestDatabase();
        assertEquals(getExpectedResultMsg(), InstanceUploaderUtils.getUploadResultMessage(ApplicationProvider.getApplicationContext(), getTestUploadResult()));
        instancesDao.deleteInstancesDatabase();
    }