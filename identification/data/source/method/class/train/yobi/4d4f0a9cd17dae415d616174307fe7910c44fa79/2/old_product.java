private static void handleLinks(Document doc){
        String[] attrNames = {"src", "href"};
        for (String attrName : attrNames) {
            Elements tags = doc.select("*[" + attrName + "]");
            for (Element tag : tags) {
                String uri = tag.attr(attrName);
                try {
                    if (!new URI(uri).isAbsolute()) {
                        tag.attr(attrName, Url.create(uri));
                    }
                } catch (URISyntaxException e) {
                    play.Logger.info("A malformed URI is ignored", e);
                }
            }
        }
    }