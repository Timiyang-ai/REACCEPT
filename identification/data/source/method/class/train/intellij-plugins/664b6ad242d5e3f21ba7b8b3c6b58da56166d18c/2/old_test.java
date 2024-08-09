    @Test
    public void checkAllValidResources() {
        IResource resource1 = new TestableResource("web.xml", "web1.xml");
        IResource resource2 = new TestableResource("web.xml", "web2.xml");
        IResource resource3 = new TestableResource("web.xml", "idontexist1.xml");
        IResource resource4 = new TestableResource("web.xml", "idontexist2.xml");

        assert PresentationLibraryElement.checkAllValidResources(new IResource[]{resource1, resource2});

        assert !PresentationLibraryElement.checkAllValidResources(new IResource[]{resource3, resource4});

        assert !PresentationLibraryElement.checkAllValidResources(new IResource[]{resource1, resource2, resource3});
    }