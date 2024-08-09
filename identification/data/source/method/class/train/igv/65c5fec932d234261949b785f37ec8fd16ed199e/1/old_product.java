public LinkedHashMap<String, List<AlignmentInterval.Row>> packAlignments(
            Iterator<Alignment> iter,
            int end,
            AlignmentTrack.RenderOptions renderOptions) {

        if(renderOptions == null) renderOptions = new AlignmentTrack.RenderOptions();

        LinkedHashMap<String, List<AlignmentInterval.Row>> packedAlignments = new LinkedHashMap<String, List<Row>>();
        boolean pairAlignments = renderOptions.isViewPairs() || renderOptions.isPairedArcView();

        if (iter == null || !iter.hasNext()) {
            return packedAlignments;
        }

        if (renderOptions.groupByOption == null) {
            List<Row> alignmentRows = new ArrayList<Row>(10000);
            pack(iter, end, pairAlignments, alignmentRows);
            packedAlignments.put("", alignmentRows);
        } else {
            // Separate alignments into groups.
            List<Alignment> nullGroup = new ArrayList<Alignment>();
            HashMap<String, List<Alignment>> groupedAlignments = new HashMap<String, List<Alignment>>();
            while (iter.hasNext()) {
                Alignment alignment = iter.next();
                String groupKey = getGroupValue(alignment, renderOptions);
                if (groupKey == null) nullGroup.add(alignment);
                else {
                    List<Alignment> group = groupedAlignments.get(groupKey);
                    if (group == null) {
                        group = new ArrayList<Alignment>(1000);
                        groupedAlignments.put(groupKey, group);
                    }
                    group.add(alignment);
                }
            }

            // Now alphabetize (sort) and pack the groups
            List<String> keys = new ArrayList<String>(groupedAlignments.keySet());
            Comparator<String> groupComparator = getGroupComparator(renderOptions.groupByOption);
            Collections.sort(keys, groupComparator);
            for (String key : keys) {
                List<Row> alignmentRows = new ArrayList<Row>(10000);
                List<Alignment> group = groupedAlignments.get(key);
                pack(group.iterator(), end, pairAlignments, alignmentRows);
                packedAlignments.put(key, alignmentRows);
            }
            List<Row> alignmentRows = new ArrayList<Row>(10000);
            pack(nullGroup.iterator(), end, pairAlignments, alignmentRows);
            packedAlignments.put("", alignmentRows);
        }

        return packedAlignments;

    }