@Test
    public void testValues()
    {
        Set<PageLayout> pageLayoutSet = EnumSet.noneOf(PageLayout.class);
        Set<String> stringSet = new HashSet<>();
        for (PageLayout pl : PageLayout.values())
        {
            String s = pl.stringValue();
            stringSet.add(s);
            pageLayoutSet.add(PageLayout.fromString(s));
        }
        assertEquals(PageLayout.values().length, pageLayoutSet.size());
        assertEquals(PageLayout.values().length, stringSet.size());
    }