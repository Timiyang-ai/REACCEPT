    @Test
    public void getPageListTest() {
        List<AppPage> queryResult = new ArrayList<>();
        queryResult.add(AppPage.builder().page("page1").appName(APPLICATION_NAME.toString()).build());
        queryResult.add(AppPage.builder().page("page2").appName(APPLICATION_NAME.toString()).build());
        when(appPageIndexAccessor.selectBy(eq(APPLICATION_NAME.toString()))).thenReturn(mockedResultMapping);
        when(mockedResultMapping.iterator()).thenReturn(queryResult.iterator());
        List<Page> result = repository.getPageList(APPLICATION_NAME);
        assertThat(result.size(), is(queryResult.size()));
        for (int i = 0; i < queryResult.size(); i++) {
            Page page = result.get(i);
            AppPage appPage = queryResult.get(i);
            assertThat(page.getName().toString(), is(appPage.getPage()));
        }
    }