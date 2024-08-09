    @Test
    public void classDeleted() {
        _fileEventListenerMock.classDeleted("com.app.pages.Page1");

        replay(_fileEventListenerMock);

        _eventsManager.addFileSystemListener(_fileEventListenerMock);
        _eventsManager.classDeleted("com.app.pages.Page1");

        verify(_fileEventListenerMock);
    }