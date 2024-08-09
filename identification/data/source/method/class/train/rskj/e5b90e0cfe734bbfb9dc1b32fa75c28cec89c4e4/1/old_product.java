public void process(Processor processor) {
        ListIterator<Entry> iterator = entries.listIterator();
        while (iterator.hasNext()) {
            boolean result = processor.process(iterator.next());
            if (result) {
                iterator.remove();
            }
        }
    }