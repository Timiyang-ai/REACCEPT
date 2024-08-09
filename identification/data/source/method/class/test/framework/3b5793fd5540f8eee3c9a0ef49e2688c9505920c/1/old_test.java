    @Test
    public void replaceComponent() {
        TabSheet tabSheet = new TabSheet();
        Label lbl1 = new Label("aaa");
        Label lbl2 = new Label("bbb");
        Label lbl3 = new Label("ccc");
        Label lbl4 = new Label("ddd");

        Tab tab1 = tabSheet.addTab(lbl1);
        tab1.setCaption("tab1");
        tab1.setClosable(true);
        Tab tab2 = tabSheet.addTab(lbl2);
        tab2.setDescription("description");
        tab2.setEnabled(false);

        // Replace component not in tabsheet with one already in tabsheet -
        // should be no-op
        tabSheet.replaceComponent(lbl3, lbl2);
        assertEquals(2, tabSheet.getComponentCount());
        assertSame(tab1, tabSheet.getTab(lbl1));
        assertSame(tab2, tabSheet.getTab(lbl2));
        assertNull(tabSheet.getTab(lbl3));

        // Replace component not in tabsheet with one not in tabsheet either
        // should add lbl4 as last tab
        tabSheet.replaceComponent(lbl3, lbl4);
        assertEquals(3, tabSheet.getComponentCount());
        assertSame(tab1, tabSheet.getTab(lbl1));
        assertSame(tab2, tabSheet.getTab(lbl2));
        assertEquals(2, tabSheet.getTabPosition(tabSheet.getTab(lbl4)));

        // Replace component in tabsheet with another
        // should swap places, tab association should stay the same but tabs
        // should swap metadata
        tabSheet.replaceComponent(lbl1, lbl2);
        assertSame(tab1, tabSheet.getTab(lbl1));
        assertSame(tab2, tabSheet.getTab(lbl2));
        assertFalse(tab1.isClosable());
        assertTrue(tab2.isClosable());
        assertFalse(tab1.isEnabled());
        assertTrue(tab2.isEnabled());
        assertEquals("description", tab1.getDescription());
        assertEquals(null, tab2.getDescription());
        assertEquals(3, tabSheet.getComponentCount());
        assertEquals(1, tabSheet.getTabPosition(tabSheet.getTab(lbl1)));
        assertEquals(0, tabSheet.getTabPosition(tabSheet.getTab(lbl2)));

        // Replace component in tabsheet with one not in tabsheet
        // should create a new tab instance for the new component, old tab
        // instance should become unattached
        // tab metadata should be copied from old to new
        tabSheet.replaceComponent(lbl1, lbl3);
        assertEquals(3, tabSheet.getComponentCount());
        assertNull(tabSheet.getTab(lbl1));
        assertNull(tab1.getComponent());
        assertNotNull(tabSheet.getTab(lbl3));
        assertFalse(tabSheet.getTab(lbl3).isEnabled());
        assertEquals("description", tab1.getDescription());
        assertEquals(1, tabSheet.getTabPosition(tabSheet.getTab(lbl3)));
    }