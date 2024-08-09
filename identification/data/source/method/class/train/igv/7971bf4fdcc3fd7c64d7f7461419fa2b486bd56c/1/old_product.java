public PackedAlignments packAlignments(
            List<AlignmentInterval> intervalList,
            AlignmentTrack.RenderOptions renderOptions) {

        if (renderOptions == null) renderOptions = new AlignmentTrack.RenderOptions();

        LinkedHashMap<String, List<Row>> packedAlignments = new LinkedHashMap<String, List<Row>>();

        boolean pairAlignments = renderOptions.isViewPairs() || renderOptions.isPairedArcView();


        if (renderOptions.groupByOption == null) {
            List<Row> alignmentRows = new ArrayList<Row>(10000);
            packAlignmentInterval(intervalList, pairAlignments, alignmentRows);
            packedAlignments.put("", alignmentRows);
        } else {
            // Separate alignments into groups.
            Table<String, Integer, List<Alignment>> groupedAlignments = HashBasedTable.create();

            for (int intervalIndex = 0; intervalIndex < intervalList.size(); intervalIndex++) {
                AlignmentInterval interval = intervalList.get(intervalIndex);
                Iterator<Alignment> iter = interval.getAlignmentIterator();
                while (iter.hasNext()) {
                    Alignment alignment = iter.next();
                    String groupKey = getGroupValue(alignment, renderOptions);
                    if (groupKey == null) {
                        groupKey = NULL_GROUP_VALUE;
                    }
                    List<Alignment> groupList = groupedAlignments.get(groupKey, intervalIndex);
                    if (groupList == null) {
                        groupList = new ArrayList<Alignment>(1000);
                        groupedAlignments.put(groupKey, intervalIndex, groupList);
                    }
                    groupList.add(alignment);
                }
            }


            // Now alphabetize (sort) and pack the groups
            List<String> keys = new ArrayList<String>(groupedAlignments.rowKeySet());
            Comparator<String> groupComparator = getGroupComparator(renderOptions.groupByOption);
            Collections.sort(keys, groupComparator);

            for (String key : keys) {
                List<Row> alignmentRows = new ArrayList<Row>(10000);
                Map<Integer, List<Alignment>> group = groupedAlignments.row(key);
                pack(group, pairAlignments, alignmentRows);
                packedAlignments.put(key, alignmentRows);
            }
            //Put null valued group at end
            List<Row> alignmentRows = new ArrayList<Row>(10000);
            Map<Integer, List<Alignment>> group = groupedAlignments.row(NULL_GROUP_VALUE);
            pack(group, pairAlignments, alignmentRows);
            packedAlignments.put("", alignmentRows);
        }

        return new PackedAlignments(intervalList, packedAlignments);
    }