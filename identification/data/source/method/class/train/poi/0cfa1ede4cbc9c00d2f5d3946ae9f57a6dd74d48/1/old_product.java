@Override
    public void removeMergedRegions(Collection<Integer> indices) {
        for (int i : (new TreeSet<Integer>(indices)).descendingSet()) {
            _sheet.removeMergedRegion(i);
        }
    }