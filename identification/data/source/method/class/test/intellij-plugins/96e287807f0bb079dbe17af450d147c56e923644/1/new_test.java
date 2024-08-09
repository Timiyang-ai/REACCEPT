    @Test
    public void allowsTemplate() {
        assert !new Mixin(_libraryMock, _classInRootPagesPackageMock, _tapestryProjectMock).allowsTemplate();
    }