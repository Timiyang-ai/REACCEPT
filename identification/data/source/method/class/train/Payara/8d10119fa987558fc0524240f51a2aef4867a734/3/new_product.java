public static final Map<String, Map<String,String>> normalize(Manifest m)
    {
        // first add the "main attributes
        Map<String, Map<String,String>> all = new HashMap<String, Map<String,String>>();
        Attributes mainAtt = m.getMainAttributes();
        all.put(MAIN_ATTS, normalize(mainAtt));

        // now add all the "sub-attributes"
        Map<String,Attributes> unwashed = m.getEntries();
        Set<Map.Entry<String,Attributes>> entries = unwashed.entrySet();

        for(Map.Entry<String,Attributes> entry : entries) {
            String name = entry.getKey();
            Attributes value = entry.getValue();

            if(name == null || value == null)
                continue;

            all.put(name, normalize(value));
        }
        return all;
    }