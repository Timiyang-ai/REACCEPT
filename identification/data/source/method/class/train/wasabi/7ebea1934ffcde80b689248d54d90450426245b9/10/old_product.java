@Override
    public List<Page> getPageList(Application.Name applicationName) {

        try {
            Collection<String> pages = driver.getKeyspace()
                    .prepareQuery(keyspace.app_page_index_CF())
                    .getKey(applicationName)
                    .execute()
                    .getResult()
                    .getColumnNames();
            if (!pages.isEmpty()) {
                List<Page> pageList = new ArrayList<>(pages.size());
                for (String pageName : pages) {
                    pageList.add(new Page.Builder().withName(Page.Name.valueOf(pageName)).build());
                }
                return pageList;
            } else {
                return EMPTY;
            }
        } catch (ConnectionException e) {
            throw new RepositoryException("Could not retrieve the pages for application:\"" + applicationName + "\"", e);
        }
    }