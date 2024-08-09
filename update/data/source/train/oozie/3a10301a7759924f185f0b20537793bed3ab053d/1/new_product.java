private void addMissingPartition(PartitionWrapper partition, String actionId) throws MetadataServiceException {
        String prefix = PartitionWrapper.makePrefix(partition.getServerName(), partition.getDbName());
        Map<String, PartitionsGroup> tablePartitionsMap;
        String tableName = partition.getTableName();
        try {
            if (hcatInstanceMap.containsKey(prefix)) {
                tablePartitionsMap = hcatInstanceMap.get(prefix);
                if (tablePartitionsMap.containsKey(tableName)) {
                    addPartitionEntry(tablePartitionsMap, tableName, partition, actionId);
                }
                else { // new table entry
                    tablePartitionsMap = new ConcurrentHashMap<String, PartitionsGroup>();
                    _createPartitionMapForTable(tablePartitionsMap, tableName, partition, actionId);
                }
            }
            else { // new partition from different hcat server/db
                _addNewEntry(hcatInstanceMap, prefix, tableName, partition, actionId);
            }

        }
        catch (ClassCastException e) {
            throw new MetadataServiceException(ErrorCode.E1501, e.getCause());
        }
        catch (NullPointerException e) {
            throw new MetadataServiceException(ErrorCode.E1501, e.getCause());
        }
        catch (IllegalArgumentException e) {
            throw new MetadataServiceException(ErrorCode.E1501, e.getCause());
        }
        catch (Exception e) {
            throw new MetadataServiceException(ErrorCode.E1501, e.getCause());
        }
    }