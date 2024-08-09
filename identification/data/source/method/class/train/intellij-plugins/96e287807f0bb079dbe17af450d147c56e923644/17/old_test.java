    @Test
    public void allowsTemplate() {
        assert new TapestryComponent(_libraryMock, _classInRootComponentsPackageMock, _tapestryProjectMock).allowsTemplate();
    }