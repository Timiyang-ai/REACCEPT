  Response backup(String key, String group) throws IOException {
    Request get = clientRequest(format("/automation/v2/backup/%s/group/%s", key, group))
        .addHeader("Accept", OCTET_STREAM.toString())
        .get()
        .build();

    return mutualSslClient.newCall(get).execute();
  }