    private void request(@NonNull TestObserver<MwPostResponse> observer) {
        final PageTitle pageTitle = new PageTitle("foo", WikiSite.forLanguageCode("en"));
        getApiService().postDescriptionEdit(pageTitle.getWikiSite().languageCode(),
                pageTitle.getWikiSite().languageCode(), pageTitle.getWikiSite().dbName(),
                pageTitle.getPrefixedText(), "some new description", "summary", MOCK_EDIT_TOKEN, null)
                .subscribe(observer);
    }