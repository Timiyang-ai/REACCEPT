@Override
    public void removeMergedRegions(Collection<Integer> indices) {
        for (int i : (new TreeSet<>(indices)).descendingSet()) {
            _sheet.removeMergedRegion(i);
        }
    }