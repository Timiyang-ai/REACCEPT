protected void writeBatches(InfluxDB influxDbConnection, String dbName, List<Point> points) {
        BatchPoints batchPoints = BatchPoints.database(dbName).build();

        for (Point point : points) {
            batchPoints.point(point);
        }

        influxDbConnection.write(batchPoints);
    }