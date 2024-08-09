@Override
    public void subscribe(URL url, NotifyListener listener) {
        this.subscribedUrl = url;
        List<URL> urls = new ArrayList<URL>();

        urls.add(url.setProtocol("mockprotocol")
                .removeParameter(Constants.CATEGORY_KEY)
                .addParameter(Constants.METHODS_KEY, "sayHello"));

        listener.notify(urls);
    }