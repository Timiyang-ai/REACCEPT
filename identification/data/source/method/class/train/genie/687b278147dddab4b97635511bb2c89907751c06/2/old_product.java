public void setName(final String name) throws GenieException {
        if (StringUtils.isBlank(name)) {
            throw new GenieException(
                    HttpURLConnection.HTTP_BAD_REQUEST,
                    "No name Entered.");
        }
        this.name = name;
    }