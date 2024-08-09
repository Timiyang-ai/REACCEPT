    @Test
    public void writeBatchesTest() {
        InfluxDB influxDbConnection = Mockito.mock(InfluxDB.class);
        Mockito.doNothing().when(influxDbConnection).write(Mockito.any(Point.class));
        Builder builder = Mockito.mock(Builder.class);
        BatchPoints batchPoints = Mockito.mock(BatchPoints.class);
        PowerMockito.mockStatic(BatchPoints.class);
        PowerMockito.when(BatchPoints.database(DEFAULT_DATABASE_NAME)).thenReturn(builder);
        Mockito.when(builder.build()).thenReturn(batchPoints);
        Map<String, String> tagsToAdd = new HashMap<>();
        tagsToAdd.put("hostId", "1");
        Map<String, Object> fieldsToAdd = new HashMap<>();
        fieldsToAdd.put("total_memory_kbs", 10000000);
        Point point = Point.measurement("measure").tag(tagsToAdd).time(System.currentTimeMillis(), TimeUnit.MILLISECONDS).fields(fieldsToAdd).build();
        List<Point> points = new ArrayList<>();
        points.add(point);
        Mockito.when(batchPoints.point(point)).thenReturn(batchPoints);

        statsCollector.writeBatches(influxDbConnection, DEFAULT_DATABASE_NAME, points);

        Mockito.verify(influxDbConnection).write(batchPoints);
    }