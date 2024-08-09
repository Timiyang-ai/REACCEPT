public Set<Evidence> getEvidence(String source, String name) {
        if (source == null || name == null) {
            return null;
        }
        final Set<Evidence> ret = new HashSet<>();
        for (Evidence e : list) {
            if (source.equals(e.getSource()) && name.equals(e.getName())) {
                ret.add(e);
            }
        }
        return ret;
    }