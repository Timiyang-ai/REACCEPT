public LinkedHashMap<String, List<AlignmentInterval.Row>> packAlignments(
            Iterator<Alignment> iter,
            int end,
            boolean pairAlignments,
            AlignmentTrack.RenderOptions renderOptions,
            int maxLevels) {

        LinkedHashMap<String, List<AlignmentInterval.Row>> packedAlignments = new LinkedHashMap<String, List<Row>>();

        if (iter == null || !iter.hasNext()) {
            return packedAlignments;
        }

        AlignmentTrack.GroupOption groupBy = renderOptions.groupByOption;
        String tag = renderOptions.getGroupByTag();

        if (groupBy == null) {
            List<Row> alignmentRows = new ArrayList(10000);
            pack(iter, end, pairAlignments, lengthComparator, alignmentRows, maxLevels);
            packedAlignments.put("", alignmentRows);
        } else {
            // Separate alignments into groups.
            List<Alignment> nullGroup = new ArrayList();
            HashMap<String, List<Alignment>> groupedAlignments = new HashMap();
            while (iter.hasNext()) {
                Alignment alignment = iter.next();
                String groupKey = getGroupValue(alignment, groupBy, tag);
                if (groupKey == null) nullGroup.add(alignment);
                else {
                    List<Alignment> group = groupedAlignments.get(groupKey);
                    if (group == null) {
                        group = new ArrayList(1000);
                        groupedAlignments.put(groupKey, group);
                    }
                    group.add(alignment);
                }
            }

            // Now alphabetize (sort) and pack the groups
            List<String> keys = new ArrayList(groupedAlignments.keySet());
            Collections.sort(keys);
            for (String key : keys) {
                List<Row> alignmentRows = new ArrayList(10000);
                List<Alignment> group = groupedAlignments.get(key);
                pack(group.iterator(), end, pairAlignments, lengthComparator, alignmentRows, maxLevels);
                packedAlignments.put(key, alignmentRows);
            }
            List<Row> alignmentRows = new ArrayList(10000);
            pack(nullGroup.iterator(), end, pairAlignments, lengthComparator, alignmentRows, maxLevels);
            packedAlignments.put("", alignmentRows);
        }

        return packedAlignments;

    }