@Test
    public void testIsExported() {
        Module thisModule = this.getClass().getModule();
        Module baseModule = Object.class.getModule();

        assertFalse(thisModule.isNamed());
        assertTrue(thisModule.isExported(""));
        assertTrue(thisModule.isExported("", thisModule));
        assertTrue(thisModule.isExported("", baseModule));
        assertTrue(thisModule.isExported("p"));
        assertTrue(thisModule.isExported("p", thisModule));
        assertTrue(thisModule.isExported("p", baseModule));

        assertTrue(baseModule.isNamed());
        assertTrue(baseModule.isExported("java.lang"));
        assertTrue(baseModule.isExported("java.lang", thisModule));
        assertFalse(baseModule.isExported("sun.reflect"));
        assertFalse(baseModule.isExported("sun.reflect", thisModule));
    }