    @Test
    public void getMessageCatalog() {
        assert new Mixin(_libraryMock, _classInRootPagesPackageMock, _tapestryProjectMock).getMessageCatalog().length == 0;
    }