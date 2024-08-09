public void setStatus(final ClusterStatus status) throws GenieException {
        if (status == null) {
            throw new GenieException(
                    HttpURLConnection.HTTP_BAD_REQUEST,
                    "No Status Entered.");
        }
        this.status = status;
    }