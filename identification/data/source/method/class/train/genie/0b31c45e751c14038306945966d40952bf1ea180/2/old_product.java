public void setId(final String id) throws GenieException {
        if (StringUtils.isBlank(this.id)) {
            this.id = id;
        } else {
            throw new GenieException(
                    HttpURLConnection.HTTP_BAD_REQUEST,
                    "Id already set for this entity.");
        }
    }