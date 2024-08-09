@Override
    public void addWhere(String where) {
        EntityVariable entityReference = createMainIdentificationVariableNN();

        where = replaceEntityPlaceholder(where, entityReference.getVariableName());

        if (StringUtils.isNotBlank(where)) {
            addWhereInternal(parseWhereCondition(where));
        }
    }