public PackedAlignments packAlignments(
            AlignmentInterval interval,
            AlignmentTrack.RenderOptions renderOptions) {

       // if (renderOptions == null) renderOptions = new AlignmentTrack.RenderOptions();

        LinkedHashMap<String, List<Row>> packedAlignments = new LinkedHashMap<String, List<Row>>();


        List<Alignment> alList = interval.getAlignments();
        // TODO -- means to undo this
        if (renderOptions.isLinkedReads()) {
            alList = linkByTag(alList, renderOptions.getLinkByTag());
        }

        if (renderOptions.groupByOption == null) {
            List<Row> alignmentRows = new ArrayList<>(10000);
            pack(alList, renderOptions, alignmentRows);
            packedAlignments.put("", alignmentRows);
        } else {

            // Separate alignments into groups.
            Map<String, List<Alignment>> groupedAlignments = new HashMap<String, List<Alignment>>();
            Iterator<Alignment> iter = alList.iterator();
            while (iter.hasNext()) {
                Alignment alignment = iter.next();
                String groupKey = getGroupValue(alignment, renderOptions);
                if (groupKey == null) {
                    groupKey = NULL_GROUP_VALUE;
                }
                List<Alignment> groupList = groupedAlignments.get(groupKey);
                if (groupList == null) {
                    groupList = new ArrayList<>(1000);
                    groupedAlignments.put(groupKey, groupList);
                }
                groupList.add(alignment);
            }


            // Now alphabetize (sort) and pack the groups
            List<String> keys = new ArrayList<String>(groupedAlignments.keySet());
            Comparator<String> groupComparator = getGroupComparator(renderOptions.groupByOption);
            Collections.sort(keys, groupComparator);

            for (String key : keys) {
                List<Row> alignmentRows = new ArrayList<>(10000);
                List<Alignment> group = groupedAlignments.get(key);
                pack(group, renderOptions, alignmentRows);
                packedAlignments.put(key, alignmentRows);
            }
        }

        List<AlignmentInterval> tmp = new ArrayList<AlignmentInterval>();
        tmp.add(interval);
        return new PackedAlignments(tmp, packedAlignments);
    }