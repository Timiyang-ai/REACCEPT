public List<TimelineItem> getTimeline() {
        List<TimelineItem> timelineItems = new ArrayList<>();
        timelineItems.addAll(comments);
        timelineItems.addAll(events);
        Collections.sort(timelineItems, new Comparator<TimelineItem>() {
            @Override
            public int compare(TimelineItem o1, TimelineItem o2) {
                return o1.getDate().compareTo(o2.getDate());
            }
        });
        return timelineItems;
    }