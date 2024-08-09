public static boolean isRichText(String str) {
        Pattern p = Pattern.compile("<[^>]+>");
        Matcher m = p.matcher(str);

        return m.find();
    }