    @Test
    public void getMessageCatalog() {
        Collection<IResource> resources1 = new ArrayList<>();
        resources1.add(new TestableResource("SomeClass.properties", "SomeClass.properties"));
        resources1.add(new TestableResource("SomeClass_pt.properties", "SomeClass_pt.properties"));
        expect(_resourceFinderMock.findLocalizedClasspathResource("com/app/pages/SomeClass.properties", true)).andReturn(resources1).anyTimes();

        Collection<IResource> resources2 = new ArrayList<>();
        resources2.add(new TestableResource("SomeClass.properties", "SomeClass.properties"));
        resources2.add(new TestableResource("SomeClass_pt.properties", "SomeClass_pt.properties"));
        expect(_resourceFinderMock.findLocalizedClasspathResource("com/app/components/folder1/SomeClass.properties", true)).andReturn(resources2).anyTimes();

        replay(_resourceFinderMock);

        assert new Page(_libraryMock, _classInRootPagesPackageMock, _tapestryProjectMock).getMessageCatalog().length == 2;

        resources1.clear();

        assert new TapestryComponent(_libraryMock, _classInSubComponentsPackageMock, _tapestryProjectMock).getMessageCatalog().length == 2;

        resources1.clear();

        assert new Page(_libraryMock, _classInRootPagesPackageMock, _tapestryProjectMock).getMessageCatalog().length == 0;
    }