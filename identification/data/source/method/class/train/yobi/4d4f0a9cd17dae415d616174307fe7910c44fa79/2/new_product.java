public static void handleLinks(Document doc){
        String hostname = Config.getHostname();
        String[] attrNames = {"src", "href"};
        Boolean noreferrer =
            play.Configuration.root().getBoolean("application.noreferrer");

        for (String attrName : attrNames) {
            Elements tags = doc.select("*[" + attrName + "]");
            for (Element tag : tags) {
                boolean isNoreferrerRequired = false;
                String uriString = tag.attr(attrName);

                if (noreferrer && attrName.equals("href")) {
                    isNoreferrerRequired = true;
                }

                try {
                    URI uri = new URI(uriString);

                    if (!uri.isAbsolute()) {
                        tag.attr(attrName, Url.create(uriString));
                    }

                    if (uri.getHost() == null || uri.getHost().equals(hostname)) {
                        isNoreferrerRequired = false;
                    }
                } catch (URISyntaxException e) {
                    play.Logger.info("A malformed URI is detected while" +
                            " checking an email to send", e);
                }

                if (isNoreferrerRequired) {
                    tag.attr("rel", tag.attr("rel") + " noreferrer");
                }
            }
        }
    }