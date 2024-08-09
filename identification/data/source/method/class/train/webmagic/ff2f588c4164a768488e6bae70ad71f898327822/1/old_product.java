public Html download(String url) {
        Page page = download(new Request(url), null);
        return (Html) page.getHtml();
    }