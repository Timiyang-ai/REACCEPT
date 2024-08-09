@Query(value = FIND_TERMINATED_CLUSTERS_SQL, nativeQuery = true)
    Set<Number> findTerminatedUnusedClusters();