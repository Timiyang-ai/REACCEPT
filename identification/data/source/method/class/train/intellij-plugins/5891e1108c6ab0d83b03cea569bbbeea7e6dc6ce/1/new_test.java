    @Test
    public void getElementNameFromClass() {
        assert new TestableParameterReceiverElement(_libraryMock, _classInRootComponentsPackageMock, _tapestryProjectMock).getName().equals("SomeClass");

        assert new TestableParameterReceiverElement(_libraryMock, _classInRootPagesPackageMock, _tapestryProjectMock).getName().equals("SomeClass");

        assert new TestableParameterReceiverElement(_libraryMock, _classInSubComponentsPackageMock, _tapestryProjectMock).getName().equals("folder1/SomeClass");
    }