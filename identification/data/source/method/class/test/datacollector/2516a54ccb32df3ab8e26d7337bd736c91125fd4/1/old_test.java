@Test
  public void testSetParameters() throws StageException {
    List<JdbcFieldColumnParamMapping> columnMapping = ImmutableList.of(
        new JdbcFieldColumnParamMapping("/field1", "P_ID", "?"),
        new JdbcFieldColumnParamMapping("/field2", "MSG", "?")
    );

    boolean caseSensitive = false;
    JdbcGenericRecordWriter writer = new JdbcGenericRecordWriter(
        connectionString,
        dataSource,
        "TEST",
        "TEST_TABLE",
        false, //rollback
        columnMapping,
        JDBCOperationType.INSERT.getCode(),
        UnsupportedOperationAction.DISCARD,
        null,
        new JdbcRecordReader(),
        caseSensitive,
        Collections.emptyList()
    );
    Record record = RecordCreator.create();
    Map<String, Field> fields = new HashMap<>();
    fields.put("field1", Field.create(100));
    fields.put("field2", Field.create("StreamSets"));
    record.set(Field.create(fields));

    SortedMap<String, String> columnsToParameters = new TreeMap<>();
    columnsToParameters.put("P_ID", "?");
    columnsToParameters.put("MSG", "?");

    String query = "INSERT INTO TEST.TEST_TABLE (MSG, P_ID) VALUES (?, ?)";
    executeSetParameters(OperationType.INSERT_CODE, query, writer, columnsToParameters, record);

    query = "DELETE FROM TEST.TEST_TABLE  WHERE P_ID = ?";
    executeSetParameters(OperationType.DELETE_CODE, query, writer, columnsToParameters, record);

    query = "UPDATE TEST.TEST_TABLE SET  MSG = ? WHERE P_ID = ?";
    fields.put("field2", Field.create("This is an updated message"));
    columnsToParameters.remove("P_ID");
    executeSetParameters(OperationType.UPDATE_CODE, query, writer, columnsToParameters, record);
  }