    @Test
    public void setUserProperties() throws Exception {
        // Mock node
        final Property oldProp = Mockito.when(Mockito.mock(Property.class).getName()).thenReturn("usr:oldProp").getMock();

        final Iterator<Property> delegateIter = ImmutableList.of(oldProp).iterator();
        final PropertyIterator propIter = Mockito.mock(PropertyIterator.class);
        Mockito.when(propIter.hasNext()).thenAnswer(invocation -> delegateIter.hasNext());
        Mockito.when(propIter.nextProperty()).thenAnswer(invocation -> delegateIter.next());

        final Node node = Mockito.mock(Node.class);
        Mockito.when(node.getProperties()).thenReturn(propIter);

        // Mock field
        final UserFieldDescriptor field = Mockito.mock(UserFieldDescriptor.class);
        Mockito.when(field.getSystemName()).thenReturn("testProp");
        Mockito.when(field.isRequired()).thenReturn(true);

        // Test setting user properties
        final Map<String, String> properties = ImmutableMap.of("testProp", "one", "碼標準萬國碼/1.1/?name=%20", "two");

        JcrPropertyUtil.setUserProperties(node, Collections.singleton(field), properties);
        Mockito.verify(node).setProperty("usr:testProp", "one");
        Mockito.verify(node).setProperty("usr:%E7%A2%BC%E6%A8%99%E6%BA%96%E8%90%AC%E5%9C%8B%E7%A2%BC%2F1.1%2F%3Fname%3D%2520", "two");
        Mockito.verify(node).getProperties();
        Mockito.verify(oldProp).remove();
        Mockito.verifyNoMoreInteractions(node);
    }