public PackedAlignments packAlignments(
            AlignmentInterval interval,
            AlignmentTrack.RenderOptions renderOptions) {

        if (renderOptions == null) renderOptions = new AlignmentTrack.RenderOptions();

        LinkedHashMap<String, List<Row>> packedAlignments = new LinkedHashMap<String, List<Row>>();

        boolean isPairedAlignments = renderOptions.isViewPairs() || renderOptions.isPairedArcView();


        if (renderOptions.groupByOption == null) {
            List<Row> alignmentRows = new ArrayList<Row>(10000);
            packInterval(interval, isPairedAlignments, alignmentRows);
            packedAlignments.put("", alignmentRows);
        } else {

            // Separate alignments into groups.
            Map<String, List<Alignment>> groupedAlignments = new HashMap<String, List<Alignment>>();
            Iterator<Alignment> iter = interval.getAlignmentIterator();
            while (iter.hasNext()) {
                Alignment alignment = iter.next();
                String groupKey = getGroupValue(alignment, renderOptions);
                if (groupKey == null) {
                    groupKey = NULL_GROUP_VALUE;
                }
                List<Alignment> groupList = groupedAlignments.get(groupKey);
                if (groupList == null) {
                    groupList = new ArrayList<Alignment>(1000);
                    groupedAlignments.put(groupKey, groupList);
                }
                groupList.add(alignment);
            }


            // Now alphabetize (sort) and packGroup the groups
            List<String> keys = new ArrayList<String>(groupedAlignments.keySet());
            Comparator<String> groupComparator = getGroupComparator(renderOptions.groupByOption);
            Collections.sort(keys, groupComparator);

            for (String key : keys) {
                List<Row> alignmentRows = new ArrayList<Row>(10000);
                List<Alignment> group = groupedAlignments.get(key);
                pack(group, isPairedAlignments, alignmentRows);
                packedAlignments.put(key, alignmentRows);
            }

            //Put null valued group at end
            List<Row> alignmentRows = new ArrayList<Row>(10000);
            List<Alignment> group = groupedAlignments.get(NULL_GROUP_VALUE);
            pack(group, isPairedAlignments, alignmentRows);
            packedAlignments.put("", alignmentRows);
        }

        List<AlignmentInterval> tmp = new ArrayList<AlignmentInterval>();
        tmp.add(interval);
        return new PackedAlignments(tmp, packedAlignments);
    }