    @Test
    public void getNumberOfColumnsTest() {
        FormEntryPrompt formEntryPrompt = mock(FormEntryPrompt.class);

        when(formEntryPrompt.getAppearanceHint()).thenReturn("");
        assertEquals(1, WidgetAppearanceUtils.getNumberOfColumns(formEntryPrompt, null));

        when(formEntryPrompt.getAppearanceHint()).thenReturn("columns-2");
        assertEquals(2, WidgetAppearanceUtils.getNumberOfColumns(formEntryPrompt, null));

        when(formEntryPrompt.getAppearanceHint()).thenReturn("columns-10");
        assertEquals(10, WidgetAppearanceUtils.getNumberOfColumns(formEntryPrompt, null));

        when(formEntryPrompt.getAppearanceHint()).thenReturn("columns-10 quick");
        assertEquals(10, WidgetAppearanceUtils.getNumberOfColumns(formEntryPrompt, null));

        when(formEntryPrompt.getAppearanceHint()).thenReturn("columns-5 autocomplete");
        assertEquals(5, WidgetAppearanceUtils.getNumberOfColumns(formEntryPrompt, null));

        when(formEntryPrompt.getAppearanceHint()).thenReturn("columns-10quick");
        assertEquals(1, WidgetAppearanceUtils.getNumberOfColumns(formEntryPrompt, null));

        when(formEntryPrompt.getAppearanceHint()).thenReturn("columns-5autocomplete");
        assertEquals(1, WidgetAppearanceUtils.getNumberOfColumns(formEntryPrompt, null));

        when(formEntryPrompt.getAppearanceHint()).thenReturn("columns-5 ");
        assertEquals(5, WidgetAppearanceUtils.getNumberOfColumns(formEntryPrompt, null));

        when(formEntryPrompt.getAppearanceHint()).thenReturn("columns-5  ");
        assertEquals(5, WidgetAppearanceUtils.getNumberOfColumns(formEntryPrompt, null));

        when(formEntryPrompt.getAppearanceHint()).thenReturn("  columns-5");
        assertEquals(5, WidgetAppearanceUtils.getNumberOfColumns(formEntryPrompt, null));

        when(formEntryPrompt.getAppearanceHint()).thenReturn("quick columns-5");
        assertEquals(5, WidgetAppearanceUtils.getNumberOfColumns(formEntryPrompt, null));

        when(formEntryPrompt.getAppearanceHint()).thenReturn("compact-5");
        assertEquals(5, WidgetAppearanceUtils.getNumberOfColumns(formEntryPrompt, null));

        when(formEntryPrompt.getAppearanceHint()).thenReturn("compact-9");
        assertEquals(9, WidgetAppearanceUtils.getNumberOfColumns(formEntryPrompt, null));

        when(formEntryPrompt.getAppearanceHint()).thenReturn("columns-9");
        assertEquals(9, WidgetAppearanceUtils.getNumberOfColumns(formEntryPrompt, null));

        when(formEntryPrompt.getAppearanceHint()).thenReturn("columns--1");
        assertEquals(1, WidgetAppearanceUtils.getNumberOfColumns(formEntryPrompt, null));

        when(formEntryPrompt.getAppearanceHint()).thenReturn("columns--10");
        assertEquals(1, WidgetAppearanceUtils.getNumberOfColumns(formEntryPrompt, null));
    }