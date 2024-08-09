public static String stripAllTags(String unsafe) {

        if (unsafe == null) {
            return null;
        }

        return Parser.unescapeEntities(Jsoup.clean(unsafe, Whitelist.none()), true);

    }