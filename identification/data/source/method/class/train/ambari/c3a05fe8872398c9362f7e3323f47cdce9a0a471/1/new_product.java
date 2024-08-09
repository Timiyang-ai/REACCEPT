@Override
  public void perform(PrerequisiteCheck prerequisiteCheck, PrereqCheckRequest request) throws AmbariException {
    final Cluster cluster = clustersProvider.get().getCluster(request.getClusterName());
    Map<String, Service> services = cluster.getServices();

    List<String> errorMessages = new ArrayList<>();

    // HDFS needs to actually prevent client retry since that causes them to try too long and not failover quickly.
    if (services.containsKey("HDFS")) {
      String clientRetryPolicyEnabled = getProperty(request, "hdfs-site", HDFS_CLIENT_RETRY_PROPERTY);
      if (null != clientRetryPolicyEnabled && Boolean.parseBoolean(clientRetryPolicyEnabled)) {
        MissingClientRetryProperty missingProperty = new MissingClientRetryProperty("HDFS",
            "hdfs-site", HDFS_CLIENT_RETRY_PROPERTY);

        prerequisiteCheck.getFailedDetail().add(missingProperty);

        errorMessages.add(getFailReason(HDFS_CLIENT_RETRY_DISABLED_KEY, prerequisiteCheck, request));
        prerequisiteCheck.getFailedOn().add("HDFS");
      }
    }

    // check hive client properties
    if (services.containsKey("HIVE")) {
      String hiveClientRetryCount = getProperty(request, "hive-site", HIVE_CLIENT_RETRY_PROPERTY);
      if (null != hiveClientRetryCount && Integer.parseInt(hiveClientRetryCount) <= 0) {
        MissingClientRetryProperty missingProperty = new MissingClientRetryProperty("HIVE",
            "hive-site", HIVE_CLIENT_RETRY_PROPERTY);

        prerequisiteCheck.getFailedDetail().add(missingProperty);

        errorMessages.add(getFailReason(HIVE_CLIENT_RETRY_MISSING_KEY, prerequisiteCheck, request));
        prerequisiteCheck.getFailedOn().add("HIVE");
      }
    }

    if (services.containsKey("OOZIE")) {
      String oozieClientRetry = getProperty(request, "oozie-env", "content");
      if (null == oozieClientRetry || !oozieClientRetry.contains(OOZIE_CLIENT_RETRY_PROPERTY)) {
        MissingClientRetryProperty missingProperty = new MissingClientRetryProperty("OOZIE",
            "oozie-env", OOZIE_CLIENT_RETRY_PROPERTY);

        prerequisiteCheck.getFailedDetail().add(missingProperty);

        errorMessages.add(getFailReason(OOZIE_CLIENT_RETRY_MISSING_KEY, prerequisiteCheck, request));
        prerequisiteCheck.getFailedOn().add("OOZIE");
      }
    }

    if (!errorMessages.isEmpty()) {
      prerequisiteCheck.setFailReason(StringUtils.join(errorMessages, " "));
      prerequisiteCheck.setStatus(PrereqCheckStatus.FAIL);
    }
  }