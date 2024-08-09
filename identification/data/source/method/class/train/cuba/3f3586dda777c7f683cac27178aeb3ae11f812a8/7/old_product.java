@Override
    public void addWhere(String where) {
        EntityReference entityReference = createMainIdentificationVariableReference();
        if (where.contains("{E}")) {
            where = entityReference.replaceEntries(where, "\\{E\\}");
        }
        addWhere(parseWhereCondition(where), entityReference, false);
    }