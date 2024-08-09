public Call<MwPostResponse> request(@NonNull WikiSite wiki,
                                        @NonNull PageTitle pageTitle,
                                        @NonNull String description,
                                        @NonNull String editToken,
                                        @NonNull Callback cb) {
        return request(ServiceFactory.get(wiki), pageTitle, description, editToken,
                AccountUtil.isLoggedIn(), cb);
    }