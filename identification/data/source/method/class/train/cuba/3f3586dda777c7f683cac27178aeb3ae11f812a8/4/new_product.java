@Override
    public void addWhere(String where) {
        EntityReferenceInferer inferrer = new EntityReferenceInferer(mainEntityName);
        EntityReference ref = inferrer.infer(queryTreeTransformer);
        boolean doReplaceVariableName = true;
        if (where.contains("{E}")) {
            doReplaceVariableName = false;
            where = ref.replaceEntries(where, "\\{E\\}");
        }
        try {
            CommonTree whereTree = Parser.parseWhereClause("where " + where);
            addWhere(whereTree, ref, doReplaceVariableName);
        } catch (RecognitionException e) {
            throw new RuntimeException(e);
        }
    }