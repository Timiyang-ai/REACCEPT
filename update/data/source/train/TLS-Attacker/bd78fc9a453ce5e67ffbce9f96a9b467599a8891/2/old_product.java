@Override
    public synchronized void onApply(Result result) {
        executedTraces++;
        for (Modification mod : result.getVector().getModificationList()) {
            ModificationCounter counter = getCounter(mod);
            if (counter == null) {
                counter = new ModificationCounter(mod.getType());
                counter.incrementCounter();
                counterList.add(counter);
            } else {
                counter.incrementCounter();
            }
        }
    }