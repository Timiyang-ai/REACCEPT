public void updateByPrimaryKey(MappedStatement ms) {
        final Class<?> entityClass = getSelectReturnType(ms);
        //映射要包含set=?和where=?
        //获取set映射
        List<ParameterMapping> parameterMappings = getColumnParameterMappings(ms);
        //获取where主键映射
        parameterMappings.addAll(getPrimaryKeyParameterMappings(ms));
        //开始拼sql
        String sql = new SQL() {{
            //update table
            UPDATE(tableName(entityClass));
            //获取全部列
            Set<EntityHelper.EntityColumn> columnList = EntityHelper.getColumns(entityClass);
            //拼所有的set column = ?
            for (EntityHelper.EntityColumn column : columnList) {
                SET(column.getColumn() + " = ?");
            }
            //where 主键=#{property} 条件
            WHERE(EntityHelper.getPrimaryKeyWhere(entityClass));
        }}.toString();
        //静态SqlSource
        StaticSqlSource sqlSource = new StaticSqlSource(ms.getConfiguration(), sql, parameterMappings);
        //替换原有的SqlSource
        setSqlSource(ms, sqlSource);
    }