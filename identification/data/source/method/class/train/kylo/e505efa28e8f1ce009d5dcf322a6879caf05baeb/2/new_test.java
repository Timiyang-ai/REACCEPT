    @Test
    public void getUserProperties() throws Exception {
        // Mock properties
        final Property prop1 = Mockito.mock(Property.class);
        Mockito.when(prop1.getName()).thenReturn("*");

        final Property prop2 = Mockito.mock(Property.class);
        Mockito.when(prop2.getName()).thenReturn("usr:testProp");
        Mockito.when(prop2.getString()).thenReturn("one");

        final Property prop3 = Mockito.mock(Property.class);
        Mockito.when(prop3.getName()).thenReturn("usr:%E7%A2%BC%E6%A8%99%E6%BA%96%E8%90%AC%E5%9C%8B%E7%A2%BC%2F1.1%2F%3Fname%3D%2520");
        Mockito.when(prop3.getString()).thenReturn("two");

        // Mock node
        final Iterator<Property> delegateIter = ImmutableList.of(prop1, prop2, prop3).iterator();
        final PropertyIterator propIter = Mockito.mock(PropertyIterator.class);
        Mockito.when(propIter.hasNext()).thenAnswer(invocation -> delegateIter.hasNext());
        Mockito.when(propIter.nextProperty()).thenAnswer(invocation -> delegateIter.next());

        final Node node = Mockito.when(Mockito.mock(Node.class).getProperties()).thenReturn(propIter).getMock();

        // Test user properties
        final Map<String, String> userProps = JcrPropertyUtil.getUserProperties(node);
        Assert.assertEquals(2, userProps.size());
        Assert.assertEquals("one", userProps.get("testProp"));
        Assert.assertEquals("two", userProps.get("碼標準萬國碼/1.1/?name=%20"));
    }