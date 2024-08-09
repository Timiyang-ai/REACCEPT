    @Test
    public void getTemplate() {
        assert new Mixin(_libraryMock, _classInRootPagesPackageMock, _tapestryProjectMock).getTemplate().length == 0;
    }