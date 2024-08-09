@Override
    public void addWhere(String where) {
        EntityReferenceInferer inferrer = new EntityReferenceInferer(getMainEntityName());
        EntityReference ref = inferrer.infer(getQueryTransformer());
        if (where.contains("{E}")) {
            where = ref.replaceEntries(where, "\\{E\\}");
        }
        try {
            CommonTree whereTree = Parser.parseWhereClause("where " + where);
            addWhere(whereTree, ref, false);
        } catch (RecognitionException e) {
            throw new RuntimeException(e);
        }
    }