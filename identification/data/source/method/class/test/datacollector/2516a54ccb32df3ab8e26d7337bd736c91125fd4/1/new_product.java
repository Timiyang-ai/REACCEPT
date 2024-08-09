@VisibleForTesting
  @SuppressWarnings("unchecked")
  int setParameters(
      int opCode,
      Map<String, String> columnsToParameters,
      final Record record,
      final Connection connection,
      PreparedStatement statement
  ) throws OnRecordErrorException {
    int paramIdx = 1;

    // Set columns and their value in query. No need to perform this for delete operation.
    if(opCode != OperationType.DELETE_CODE) {
      paramIdx = setParamsToStatement(paramIdx, statement, columnsToParameters, record, connection, opCode);
    }
    // Set primary keys in WHERE clause for update and delete operations
    if(opCode != OperationType.INSERT_CODE){
      paramIdx = setPrimaryKeys(paramIdx, record, statement, opCode);
    }
    return paramIdx;
  }