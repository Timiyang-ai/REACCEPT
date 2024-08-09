public List<AlignmentInterval.Row> packAlignments(
            Iterator<Alignment> iter,
            int end,
            boolean pairAlignments,
            AlignmentTrack.SortOption groupBy,
            int maxLevels) {


        List<Row> alignmentRows = new ArrayList(1000);
        if (iter == null || !iter.hasNext()) {
            return alignmentRows;
        }

        if (groupBy == null) {
            pack(iter, end, pairAlignments, lengthComparator, alignmentRows, maxLevels);
        } else {
            // Separate by group
            List<Alignment> nullGroup = new ArrayList();
            HashMap<String, List<Alignment>> groupedAlignments = new HashMap();
            while (iter.hasNext()) {
                Alignment al = iter.next();
                String groupKey = getGroupValue(al, groupBy);
                if (groupKey == null) nullGroup.add(al);
                else {
                    List<Alignment> group = groupedAlignments.get(groupKey);
                    if (group == null) {
                        group = new ArrayList(1000);
                        groupedAlignments.put(groupKey, group);
                    }
                    group.add(al);
                }
            }
            List<String> keys = new ArrayList(groupedAlignments.keySet());
            Collections.sort(keys);
            for (String key : keys) {
                List<Alignment> group = groupedAlignments.get(key);
                pack(group.iterator(), end, pairAlignments, lengthComparator, alignmentRows, maxLevels);
            }
            pack(nullGroup.iterator(), end, pairAlignments, lengthComparator, alignmentRows, maxLevels);
        }

        return alignmentRows;

    }