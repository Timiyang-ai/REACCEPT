@Modifying
    @Query(value = DELETE_TERMINATED_CLUSTERS_SQL, nativeQuery = true)
    int deleteTerminatedClusters();