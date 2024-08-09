@Override
    public void addWhere(String where) {
        EntityVariable entityReference = createMainIdentificationVariableNN();

        where = replaceEntityPlaceholder(where, entityReference.getVariableName());

        addWhereInternal(parseWhereCondition(where));
    }