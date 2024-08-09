    @Test
    public void classCreated() {
        _fileEventListenerMock.classCreated("com.app.pages.Page1");

        replay(_fileEventListenerMock);

        _eventsManager.addFileSystemListener(_fileEventListenerMock);
        _eventsManager.classCreated("com.app.pages.Page1");

        verify(_fileEventListenerMock);
    }