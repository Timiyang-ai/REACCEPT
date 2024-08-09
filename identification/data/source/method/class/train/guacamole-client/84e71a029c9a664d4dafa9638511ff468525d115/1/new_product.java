public static void parseUserInfo(String userInfo, 
            GuacamoleConfiguration config)
            throws UnsupportedEncodingException {

        Matcher userinfoMatcher = userinfoPattern.matcher(userInfo);

        if (userinfoMatcher.matches()) {
            String username = userinfoMatcher.group(USERNAME_GROUP);
            String password = userinfoMatcher.group(PASSWORD_GROUP);

            if (username != null && !username.isEmpty())
                config.setParameter("username",
                        URLDecoder.decode(username, "UTF-8"));

            if (password != null && !password.isEmpty())
                config.setParameter("password",
                        URLDecoder.decode(password, "UTF-8"));
        }

    }