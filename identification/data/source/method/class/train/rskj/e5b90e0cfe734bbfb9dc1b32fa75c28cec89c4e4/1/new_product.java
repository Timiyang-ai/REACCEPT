public void process(int maxIterations, Processor processor) {
        ListIterator<Entry> iterator = entries.listIterator();
        List<Entry> toRetry = new ArrayList<>();
        int i = 0;
        while (iterator.hasNext() && i < maxIterations) {
            Entry entry = iterator.next();
            iterator.remove();
            ++i;

            if (!processor.process(entry)) {
                toRetry.add(entry);
            }
        }

        entries.addAll(toRetry);
    }