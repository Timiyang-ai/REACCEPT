public boolean isExported(String pn, Module target) {
        Objects.requireNonNull(pn);

        // all packages are exported by unnamed modules
        if (!isNamed())
            return true;

        Map<String, Map<Module, Boolean>>  exports = this.exports; // volatile read
        Map<Module, Boolean> targets = exports.get(pn);
        if (targets != null) {
            // unqualified export or exported to 'target'
            if (targets.isEmpty() || targets.containsKey(target)) return true;
        }

        // not exported
        return false;
    }