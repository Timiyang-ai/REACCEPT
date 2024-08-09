    @Test
    @PrepareForTest({File.class, Script.class, ConfigDriveBuilder.class})
    public void linkUserDataTestUserDataFilePathDoesNotExist() throws Exception {
        File fileMock = Mockito.mock(File.class);
        Mockito.doReturn(false).when(fileMock).exists();

        PowerMockito.mockStatic(File.class, Script.class);
        PowerMockito.whenNew(File.class).withArguments(Mockito.anyString()).thenReturn(fileMock);

        Script scriptMock = Mockito.mock(Script.class);
        PowerMockito.whenNew(Script.class).withAnyArguments().thenReturn(scriptMock);

        ConfigDriveBuilder.linkUserData("test");

        Mockito.verify(scriptMock, times(0)).execute();
    }