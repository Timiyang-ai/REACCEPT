@Override
    public void addWhere(String where) {
        EntityReference entityReference = createMainIdentificationVariableReference();
        if (where.contains("{E}")) {
            where = entityReference.replaceEntries(where, "\\{E\\}");
        }
        try {
            CommonTree whereTree = Parser.parseWhereClause("where " + where);
            addWhere(whereTree, entityReference, false);
        } catch (RecognitionException e) {
            throw new RuntimeException(e);
        }
    }