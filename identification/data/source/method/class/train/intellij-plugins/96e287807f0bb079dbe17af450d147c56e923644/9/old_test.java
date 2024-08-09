    @Test
    public void fileCreated() {
        _fileEventListenerMock.fileCreated("some/path");

        replay(_fileEventListenerMock);

        _eventsManager.addFileSystemListener(_fileEventListenerMock);
        _eventsManager.fileCreated("some/path");

        verify(_fileEventListenerMock);
    }