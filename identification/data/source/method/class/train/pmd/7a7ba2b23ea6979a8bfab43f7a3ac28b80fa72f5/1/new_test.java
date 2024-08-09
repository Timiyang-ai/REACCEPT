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

        Assert.assertEquals(by(Double.TYPE, "double"), by(null, "double"));
        Assert.assertEquals(by(Double.class, "Double"), by(null, "double"));
        Assert.assertEquals(by(Character.class, "Character"), by(null, "char"));
        Assert.assertEquals(by(Double.TYPE, "double"), by(null, "float"));
        Assert.assertEquals(by(Double.TYPE, "double"), by(null, "int"));
        Assert.assertEquals(by(Double.TYPE, "double"), by(Integer.class, "Integer"));
        Assert.assertEquals(by(Double.TYPE, "double"), by(null, "long"));
        Assert.assertEquals(by(Double.TYPE, "double"), by(Long.TYPE, "long"));
        Assert.assertEquals(by(Double.TYPE, "double"), by(Long.class, "Long"));
        Assert.assertEquals(by(Float.TYPE, "float"), by(null, "int"));
        Assert.assertEquals(by(Float.TYPE, "float"), by(Integer.TYPE, "int"));
        Assert.assertEquals(by(Float.TYPE, "float"), by(Integer.class, "Integer"));
        Assert.assertEquals(by(Float.TYPE, "float"), by(null, "long"));
        Assert.assertEquals(by(Float.TYPE, "float"), by(Long.TYPE, "long"));
        Assert.assertEquals(by(Float.TYPE, "float"), by(Long.class, "Long"));
        Assert.assertEquals(by(Integer.TYPE, "int"), by(null, "char"));
        Assert.assertEquals(by(Integer.TYPE, "int"), by(Character.TYPE, "char"));
        Assert.assertEquals(by(Integer.TYPE, "int"), by(Character.class, "Character"));
        Assert.assertEquals(by(Long.TYPE, "long"), by(null, "int"));
        Assert.assertEquals(by(Long.TYPE, "long"), by(Integer.TYPE, "int"));
        Assert.assertEquals(by(Long.TYPE, "long"), by(Integer.class, "Integer"));
        Assert.assertEquals(by(Long.TYPE, "long"), by(null, "char"));
        Assert.assertEquals(by(Long.TYPE, "long"), by(Character.TYPE, "char"));
        Assert.assertEquals(by(Long.TYPE, "long"), by(Character.class, "Character"));
    }