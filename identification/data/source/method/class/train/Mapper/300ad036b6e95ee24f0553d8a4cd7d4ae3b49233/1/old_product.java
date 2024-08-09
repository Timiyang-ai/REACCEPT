public SqlNode updateByPrimaryKeySelective(MappedStatement ms) {
        Class<?> entityClass = getSelectReturnType(ms);
        List<SqlNode> sqlNodes = new ArrayList<SqlNode>();
        //update table
        sqlNodes.add(new StaticTextSqlNode("UPDATE " + tableName(entityClass)));
        //获取全部列
        Set<EntityHelper.EntityColumn> columnList = EntityHelper.getColumns(entityClass);
        List<SqlNode> ifNodes = new ArrayList<SqlNode>();
        //全部的if property!=null and property!=''
        for (EntityHelper.EntityColumn column : columnList) {
            StaticTextSqlNode columnNode = new StaticTextSqlNode(column.getColumn() + " = #{" + column.getProperty() + "}, ");
            ifNodes.add(getIfNotNull(column, columnNode));
        }
        sqlNodes.add(new SetSqlNode(ms.getConfiguration(), new MixedSqlNode(ifNodes)));
        //获取全部的主键的列
        columnList = EntityHelper.getPKColumns(entityClass);
        List<SqlNode> whereNodes = new ArrayList<SqlNode>();
        boolean first = true;
        //where 主键=#{property} 条件
        for (EntityHelper.EntityColumn column : columnList) {
            whereNodes.add(getColumnEqualsProperty(column, first));
            first = false;
        }
        sqlNodes.add(new WhereSqlNode(ms.getConfiguration(), new MixedSqlNode(whereNodes)));
        return new MixedSqlNode(sqlNodes);
    }