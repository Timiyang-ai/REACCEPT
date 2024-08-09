    @Test(expected = CloudRuntimeException.class)
    public void buildConfigDriveTestNoVmData() {
        ConfigDriveBuilder.buildConfigDrive(null, "teste", "C:");
    }