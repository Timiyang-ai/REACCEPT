    @Test
    public void compareTo() {
        JavaFieldMock fieldMock = new JavaFieldMock("field1", true);
        JavaFieldMock fieldMock2 = new JavaFieldMock("field2", true);
        TapestryComponent componentMock = org.easymock.EasyMock.createMock(TapestryComponent.class);

        assert new InjectedElement(fieldMock, componentMock).compareTo(new InjectedElement(fieldMock, componentMock)) == 0;

        assert new InjectedElement(fieldMock, componentMock).compareTo(new InjectedElement(fieldMock2, componentMock)) < 0;
    }