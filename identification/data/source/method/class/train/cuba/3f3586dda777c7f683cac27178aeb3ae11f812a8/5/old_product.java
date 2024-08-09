@Override
    public void addWhere(String where) {
        EntityVariable entityReference = createMainIdentificationVariable();

        where = replaceEntityPlaceholder(where, entityReference.getVariableName());

        addWhereInternal(parseWhereCondition(where));
    }