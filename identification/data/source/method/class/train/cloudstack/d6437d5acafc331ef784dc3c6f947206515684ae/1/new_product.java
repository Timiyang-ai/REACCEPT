protected void writeBatches(InfluxDB influxDbConnection, String dbName, List<Point> points) {
        BatchPoints batchPoints = BatchPoints.database(dbName).build();
        influxDbConnection.enableBatch(BatchOptions.DEFAULTS);

        for (Point point : points) {
            batchPoints.point(point);
        }

        influxDbConnection.write(batchPoints);
    }