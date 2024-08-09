public static String capitalize(String s) {
        if(s==null || s.equals(""))
            return "";

        String capedS = ""+Character.toUpperCase(s.charAt(0));
        int len = s.length();
        if(len>1)
            capedS += s.substring(1, len).toLowerCase();

        return capedS;
    }