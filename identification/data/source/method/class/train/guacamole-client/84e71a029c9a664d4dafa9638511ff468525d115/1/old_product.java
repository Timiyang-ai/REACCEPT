public static Map<String, String> parseUserInfo(String userInfo)
            throws UnsupportedEncodingException {

        Map<String, String> userInfoMap = new HashMap<String, String>();
        Matcher userinfoMatcher = userinfoPattern.matcher(userInfo);

        if (userinfoMatcher.matches()) {
            String username = URLDecoder.decode(
                    userinfoMatcher.group(USERNAME_GROUP), "UTF-8");
            String password = URLDecoder.decode(
                    userinfoMatcher.group(PASSWORD_GROUP), "UTF-8");

            if (username != null && !username.isEmpty())
                userInfoMap.put("username", username);

            if (password != null && !password.isEmpty())
                userInfoMap.put("password", password);
        }

        return userInfoMap;

    }