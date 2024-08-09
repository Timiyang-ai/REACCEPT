    @Test
    public void fileContentsChanged() {
        TestableResource resource = new TestableResource("", "");

        _fileEventListenerMock.fileContentsChanged(resource);

        replay(_fileEventListenerMock);

        _eventsManager.addFileSystemListener(_fileEventListenerMock);
        _eventsManager.fileContentsChanged(resource);

        verify(_fileEventListenerMock);
    }