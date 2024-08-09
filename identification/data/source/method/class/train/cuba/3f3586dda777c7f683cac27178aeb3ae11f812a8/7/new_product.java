@Override
    public void addWhere(String where) {
        EntityReference entityReference = createMainIdentificationVariableReference();
        if (where.contains("{E}")) {
            where = entityReference.replaceEntries(where, "\\{E\\}");
        }
        if (StringUtils.isNotBlank(where)) {
            addWhere(parseWhereCondition(where), entityReference, false);
        }
    }