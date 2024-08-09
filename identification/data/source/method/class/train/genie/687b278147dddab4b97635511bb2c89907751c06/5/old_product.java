public void setUser(final String user) throws GenieException {
        if (StringUtils.isBlank(user)) {
            throw new GenieException(
                    HttpURLConnection.HTTP_BAD_REQUEST,
                    "No user Entered.");
        }
        this.user = user;
    }