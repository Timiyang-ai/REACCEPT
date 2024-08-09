    @Test
    public void toUserProperties() {
        // Mock user field descriptors
        final UserFieldDescriptor field1 = Mockito.mock(UserFieldDescriptor.class);
        Mockito.when(field1.getDescription()).thenReturn("Global field 1");
        Mockito.when(field1.getDisplayName()).thenReturn("Test1");
        Mockito.when(field1.getOrder()).thenReturn(0);
        Mockito.when(field1.getSystemName()).thenReturn("field1");

        final UserFieldDescriptor field2 = Mockito.mock(UserFieldDescriptor.class);
        Mockito.when(field2.getDescription()).thenReturn("Global field 2");
        Mockito.when(field2.getDisplayName()).thenReturn("Test2");
        Mockito.when(field2.getOrder()).thenReturn(1);
        Mockito.when(field2.isRequired()).thenReturn(true);
        Mockito.when(field2.getSystemName()).thenReturn("field2");

        final UserFieldDescriptor field3 = Mockito.mock(UserFieldDescriptor.class);
        Mockito.when(field3.getDescription()).thenReturn("Category field 1");
        Mockito.when(field3.getDisplayName()).thenReturn("Overridden");
        Mockito.when(field3.getOrder()).thenReturn(0);
        Mockito.when(field3.isRequired()).thenReturn(true);
        Mockito.when(field3.getSystemName()).thenReturn("field1");

        final UserFieldDescriptor field4 = Mockito.mock(UserFieldDescriptor.class);
        Mockito.when(field4.getDescription()).thenReturn("Category field 2");
        Mockito.when(field4.getDisplayName()).thenReturn("Test4");
        Mockito.when(field4.getOrder()).thenReturn(0);
        Mockito.when(field4.getSystemName()).thenReturn("field4");

        // Verify user properties
        final Map<String, String> properties = ImmutableMap.of("field1", "one", "field2", "two", "field4", "three", "customField", "four");
        final Set<UserProperty> userProperties = UserPropertyTransform.toUserProperties(properties, ImmutableSet.of(field2, field1), ImmutableSet.of(field3, field4));
        Assert.assertEquals(4, userProperties.size());

        final UserProperty[] array = userProperties.toArray(new UserProperty[4]);
        Assert.assertEquals("Global field 1", array[0].getDescription());
        Assert.assertEquals("Test1", array[0].getDisplayName());
        Assert.assertTrue(array[0].isLocked());
        Assert.assertEquals(0, array[0].getOrder().intValue());
        Assert.assertFalse(array[0].isRequired());
        Assert.assertEquals("field1", array[0].getSystemName());
        Assert.assertEquals("one", array[0].getValue());

        Assert.assertEquals("Global field 2", array[1].getDescription());
        Assert.assertEquals("Test2", array[1].getDisplayName());
        Assert.assertTrue(array[1].isLocked());
        Assert.assertEquals(1, array[1].getOrder().intValue());
        Assert.assertTrue(array[1].isRequired());
        Assert.assertEquals("field2", array[1].getSystemName());
        Assert.assertEquals("two", array[1].getValue());

        Assert.assertEquals("Category field 2", array[2].getDescription());
        Assert.assertEquals("Test4", array[2].getDisplayName());
        Assert.assertTrue(array[2].isLocked());
        Assert.assertEquals(2, array[2].getOrder().intValue());
        Assert.assertFalse(array[2].isRequired());
        Assert.assertEquals("field4", array[2].getSystemName());
        Assert.assertEquals("three", array[2].getValue());

        Assert.assertNull(array[3].getDescription());
        Assert.assertNull(array[3].getDisplayName());
        Assert.assertFalse(array[3].isLocked());
        Assert.assertNull(array[3].getOrder());
        Assert.assertNull(array[3].isRequired());
        Assert.assertEquals("customField", array[3].getSystemName());
        Assert.assertEquals("four", array[3].getValue());
    }