    @Test
    public void modelChanged() {
        _tapestryListenerMock.modelChanged();

        replay(_tapestryListenerMock);

        _eventsManager.addTapestryModelListener(_tapestryListenerMock);
        _eventsManager.modelChanged();

        verify(_tapestryListenerMock);
    }