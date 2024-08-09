public static Set<Long> generateMandatoryCuboidSet(Map<Long, Long> statistics, Map<Long, Long> hitFrequencyMap,
            Map<Long, Map<Long, Long>> rollingUpCountSourceMap, final long rollUpThresholdForMandatory) {
        Set<Long> mandatoryCuboidSet = Sets.newHashSet();
        if (hitFrequencyMap == null || hitFrequencyMap.isEmpty() || rollingUpCountSourceMap == null
                || rollingUpCountSourceMap.isEmpty()) {
            return mandatoryCuboidSet;
        }
        long totalHitFrequency = 0L;
        for (long hitFrequency : hitFrequencyMap.values()) {
            totalHitFrequency += hitFrequency;
        }

        if (totalHitFrequency == 0) {
            return mandatoryCuboidSet;
        }

        for (Map.Entry<Long, Long> hitFrequency : hitFrequencyMap.entrySet()) {
            long cuboid = hitFrequency.getKey();

            if (isCuboidMandatory(cuboid, statistics, rollingUpCountSourceMap)) {
                long totalEstScanCount = 0L;
                for (long estScanCount : rollingUpCountSourceMap.get(cuboid).values()) {
                    totalEstScanCount += estScanCount;
                }
                totalEstScanCount /= rollingUpCountSourceMap.get(cuboid).size();
                if ((hitFrequency.getValue() * 1.0 / totalHitFrequency)
                        * totalEstScanCount >= rollUpThresholdForMandatory) {
                    mandatoryCuboidSet.add(cuboid);
                }
            }
        }
        return mandatoryCuboidSet;
    }