@Test
    public void testEquals() {
        Assert.assertEquals(byClass(SimpleTypedNameDeclaration.class), byClass(SimpleTypedNameDeclaration.class));
        Assert.assertEquals(byClass(List.class), byClass(ArrayList.class));
        Assert.assertEquals(byClass(ArrayList.class), byClass(List.class));
        Assert.assertEquals(byName("String"), byName("String"));
        Assert.assertEquals(byClass(String.class), byName("String"));
        Assert.assertEquals(byClass(JComponent.class), byClass(JTextField.class));

        Assert.assertFalse(byClass(Map.class).equals(byClass(List.class)));
        Assert.assertFalse(byName("A").equals(byName("B")));
        Assert.assertFalse(byClass(String.class).equals(byName("A")));
    }