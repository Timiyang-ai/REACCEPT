public boolean isExported(String pn, Module target) {
        Objects.requireNonNull(pn);
        Objects.requireNonNull(target);
        return implIsExported(pn, target);
    }