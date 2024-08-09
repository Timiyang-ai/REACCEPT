public TupleFilter flatFilter() {
        return flatFilter(KylinConfig.getInstanceFromEnv().getFlatFilterMaxChildrenSize());
    }