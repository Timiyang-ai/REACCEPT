    @Test
    public void compareTo() {
        String templateMock = "template1";
        String templateMock2 = "template2";
        InjectedElement injectedElement = org.easymock.EasyMock.createMock(InjectedElement.class);


        assert new TemplateElement(injectedElement, templateMock).compareTo(new TemplateElement(injectedElement, templateMock)) == 0;

        assert new TemplateElement(injectedElement, templateMock).compareTo(new TemplateElement(injectedElement, templateMock2)) < 0;
    }