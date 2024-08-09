public static Map<String, List<MigrateTask>> balanceExpand(String table, Map<Integer, List<Range>> integerListMap, List<String> oldDataNodes,
                                                               List<String> newDataNodes, int slotsTotalNum) {

        int newNodeSize = oldDataNodes.size() + newDataNodes.size();
        int newSlotPerNode = slotsTotalNum / newNodeSize;
        Map<String, List<MigrateTask>> newNodeTask = new HashMap<>();
        int gb = slotsTotalNum - newSlotPerNode * (newNodeSize);
        for (int i = 0; i < integerListMap.size(); i++) {

            List<Range> rangeList = integerListMap.get(i);
            int needMoveNum = getCurTotalSize(rangeList) - newSlotPerNode;
            List<Range> allMoveList = getPartAndRemove(rangeList, needMoveNum);
            for (int i1 = 0; i1 < newDataNodes.size(); i1++) {
                String newDataNode = newDataNodes.get(i1);
                if (allMoveList.size() == 0)
                    break;
                List<MigrateTask> curRangeList = newNodeTask.get(newDataNode);
                if (curRangeList == null)
                    curRangeList = new ArrayList<>();
                int hasSlots = getCurTotalSizeForTask(curRangeList);
                int needMove = (i1 == 0) ? newSlotPerNode - hasSlots + gb : newSlotPerNode - hasSlots;
                if (needMove > 0) {
                    List<Range> moveList = getPartAndRemove(allMoveList, needMove);
                    MigrateTask task = new MigrateTask();
                    if (i >= oldDataNodes.size()) {
                        throw new RuntimeException(String.format("crc32slot_%s.properties does not match schema table dataNode.",table.toUpperCase()));
                    }
                    task.setFrom(oldDataNodes.get(i));
                    task.setTo(newDataNode);
                    task.setTable(table);
                    task.setSlots(moveList);
                    curRangeList.add(task);
                    newNodeTask.put(newDataNode, curRangeList);
                }
            }

            if (allMoveList.size() > 0) {
                throw new RuntimeException("some slot has not moved to");
            }


        }

        return newNodeTask;
    }