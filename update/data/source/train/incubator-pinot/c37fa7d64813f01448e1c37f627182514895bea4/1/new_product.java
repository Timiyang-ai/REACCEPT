public void setUpNewTable(TableConfig tableConfig, IdealState idealState) {
    Preconditions.checkState(!_isStopping, "Segment manager is stopping");

    String realtimeTableName = tableConfig.getTableName();
    LOGGER.info("Setting up new LLC table: {}", realtimeTableName);

    // Make sure all the existing segments are HLC segments
    List<String> currentSegments = getAllSegments(realtimeTableName);
    for (String segmentName : currentSegments) {
      // TODO: Should return 4xx HTTP status code. Currently all exceptions are returning 500
      Preconditions.checkState(SegmentName.isHighLevelConsumerSegmentName(segmentName),
          "Cannot set up new LLC table: %s with existing non-HLC segment: %s", realtimeTableName, segmentName);
    }

    _flushThresholdUpdateManager.clearFlushThresholdUpdater(realtimeTableName);

    PartitionLevelStreamConfig streamConfig =
        new PartitionLevelStreamConfig(tableConfig.getTableName(), tableConfig.getIndexingConfig().getStreamConfigs());
    InstancePartitions instancePartitions = getConsumingInstancePartitions(tableConfig);
    int numPartitions = getNumPartitions(streamConfig);
    int numReplicas = getNumReplicas(tableConfig, instancePartitions);

    SegmentAssignment segmentAssignment = SegmentAssignmentFactory.getSegmentAssignment(_helixManager, tableConfig);
    Map<InstancePartitionsType, InstancePartitions> instancePartitionsMap =
        Collections.singletonMap(InstancePartitionsType.CONSUMING, instancePartitions);

    long currentTimeMs = getCurrentTimeMs();
    Map<String, Map<String, String>> instanceStatesMap = idealState.getRecord().getMapFields();
    for (int partitionId = 0; partitionId < numPartitions; partitionId++) {
      String segmentName =
          setupNewPartition(tableConfig, streamConfig, partitionId, currentTimeMs, instancePartitions, numPartitions,
              numReplicas);
      updateInstanceStatesForNewConsumingSegment(instanceStatesMap, null, segmentName, segmentAssignment,
          instancePartitionsMap);
    }

    setIdealState(realtimeTableName, idealState);
  }