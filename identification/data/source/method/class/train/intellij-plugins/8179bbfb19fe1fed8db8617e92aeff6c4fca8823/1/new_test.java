    @Test
    public void createElementInstance_component() {
        assert PresentationLibraryElement.createElementInstance(_libraryMock, _classInRootComponentsPackageMock, _tapestryProjectMock) instanceof TapestryComponent;
    }