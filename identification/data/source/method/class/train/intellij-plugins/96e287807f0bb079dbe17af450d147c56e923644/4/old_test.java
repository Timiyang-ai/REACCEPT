    @Test
    public void fileDeleted() {
        _fileEventListenerMock.fileDeleted("some/path");

        replay(_fileEventListenerMock);

        _eventsManager.addFileSystemListener(_fileEventListenerMock);
        _eventsManager.fileDeleted("some/path");

        verify(_fileEventListenerMock);
    }