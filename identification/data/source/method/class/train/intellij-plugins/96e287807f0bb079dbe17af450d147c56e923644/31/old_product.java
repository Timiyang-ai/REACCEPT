public int compareTo(Object o) {
        return getElementId().compareTo(((InjectedElement) o).getElementId());
    }