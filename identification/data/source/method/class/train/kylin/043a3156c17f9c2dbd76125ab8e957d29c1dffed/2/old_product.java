public static void complementRowCountForMandatoryCuboids(Map<Long, Long> statistics, long baseCuboid,
            Set<Long> mandatoryCuboidSet) {
        // Sort entries order by row count asc
        SortedSet<Map.Entry<Long, Long>> sortedStatsSet = new TreeSet<Map.Entry<Long, Long>>(
                new Comparator<Map.Entry<Long, Long>>() {
                    public int compare(Map.Entry<Long, Long> o1, Map.Entry<Long, Long> o2) {
                        return o1.getValue().compareTo(o2.getValue());
                    }
                });
        sortedStatsSet.addAll(statistics.entrySet());
        for (Long cuboid : mandatoryCuboidSet) {
            if (statistics.get(cuboid) == null) {
                // Get estimate row count for mandatory cuboid
                long tmpRowCount = -1;
                for (Map.Entry<Long, Long> entry : sortedStatsSet) {
                    if (isDescendant(cuboid, entry.getKey())) {
                        tmpRowCount = entry.getValue();
                    }
                }
                statistics.put(cuboid, tmpRowCount < 0 ? statistics.get(baseCuboid) : tmpRowCount);
            }
        }
    }