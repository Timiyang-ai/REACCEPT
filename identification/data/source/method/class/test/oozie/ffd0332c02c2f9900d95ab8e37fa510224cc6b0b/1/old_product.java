public boolean removePartition(PartitionWrapper partition, boolean cascade) {
        String prefix = PartitionWrapper.makePrefix(partition.getServerName(), partition.getDbName());
        if (hcatInstanceMap.containsKey(prefix)) {
            Map<String, PartitionsGroup> tableMap = hcatInstanceMap.get(prefix);
            String tableName = partition.getTableName();
            if (tableMap.containsKey(tableName)) {
                PartitionsGroup missingPartitions = tableMap.get(tableName);
                if (missingPartitions != null) {
                    missingPartitions.getPartitionsMap().remove(partition);
                    // cascading removal
                    if (cascade) {
                        if (missingPartitions.getPartitionsMap().size() == 0) {
                            tableMap.remove(tableName);
                            if (tableMap.size() == 0) {
                                hcatInstanceMap.remove(prefix);
                            }
                        }
                    }
                    return true;
                }
                else {
                    log.warn("No partition entries for table [{0}]", tableName);
                }
            }
            else {
                log.warn("HCat table [{0}] not found", tableName);
            }
        }
        else {
            log.warn("HCat instance entry [{0}] not found", prefix);
        }
        return false;
    }