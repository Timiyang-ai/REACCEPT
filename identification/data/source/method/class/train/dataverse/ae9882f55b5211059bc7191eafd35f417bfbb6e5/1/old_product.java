public static String stripAllTags(String unsafe){
        
        if (unsafe == null){
            return null;
        }        
        
        return Jsoup.clean(unsafe, Whitelist.none());
        
    }