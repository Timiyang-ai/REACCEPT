public Call<DescriptionEdit> request(@NonNull WikiSite wiki,
                                         @NonNull PageTitle pageTitle,
                                         @NonNull String description,
                                         @NonNull String editToken,
                                         @NonNull Callback cb) {
        return request(cachedService.service(wiki), pageTitle, description, editToken,
                AccountUtil.isLoggedIn(), cb);
    }