public Call<DescriptionEdit> request(@NonNull PageTitle pageTitle,
                                         @NonNull String description,
                                         @NonNull Callback cb) {
        return request(cachedService.service(WIKI_DATA_SITE), pageTitle, description,
                pageTitle.getWikiSite().languageCode(), User.isLoggedIn(), cb);
    }