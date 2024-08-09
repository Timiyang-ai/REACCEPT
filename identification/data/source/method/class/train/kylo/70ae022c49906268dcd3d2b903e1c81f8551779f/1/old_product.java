public static long convertToDelimitedStream(final ResultSet rs, final OutputStream outStream, final RowVisitor visitor, String delimiter) throws SQLException, IOException {
        // avoid overflowing log with redundant messages
        int dateConversionWarning = 0;

        if (rs == null || rs.getMetaData() == null) {
            log.warn("Received empty resultset or no metadata.");
            return 0;
        }
        OutputStreamWriter writer = new OutputStreamWriter(outStream);
        final ResultSetMetaData meta = rs.getMetaData();
        final DelimiterEscaper escaper = new DelimiterEscaper(delimiter);

        // Write header
        final int nrOfColumns = meta.getColumnCount();
        StringBuffer sb = new StringBuffer();
        for (int i = 1; i <= nrOfColumns; i++) {
            String columnName = meta.getColumnName(i);
            sb.append(escaper.translate(columnName));
            if (i != nrOfColumns) {
                sb.append(delimiter);
            } else {
                sb.append("\n");
            }
        }
        writer.append(sb.toString());
        long nrOfRows = 0;
        while (rs.next()) {
            if (visitor != null) {
                visitor.visitRow(rs);
            }
            sb = new StringBuffer();
            nrOfRows++;
            for (int i = 1; i <= nrOfColumns; i++) {
                String val = null;

                int colType = meta.getColumnType(i);
                if (colType == Types.DATE || colType == Types.TIMESTAMP) {
                    Timestamp sqlDate = null;
                    try {
                        // Extract timestamp
                        sqlDate = extractSqlDate(rs, i);
                    } catch (Exception e) {
                        // Still failed, maybe exotic date type
                        if (dateConversionWarning++ < 10) {
                            log.warn("{} is not convertible to timestamp or date", rs.getMetaData().getColumnName(i));
                        }
                    }

                    if (visitor != null) {
                        visitor.visitColumn(rs.getMetaData().getColumnName(i), colType, sqlDate);
                    }
                    if (sqlDate != null) {
                        DateTimeFormatter formatter = ISODateTimeFormat.dateTime().withZoneUTC();
                        val = formatter.print(new DateTime(sqlDate.getTime()));
                    }
                } else if (colType == Types.TIME) {
                    Time time = rs.getTime(i);
                    if (visitor != null) {
                        visitor.visitColumn(rs.getMetaData().getColumnName(i), colType, time);
                    }
                    DateTimeFormatter formatter = ISODateTimeFormat.time().withZoneUTC();
                    val = formatter.print(new DateTime(time.getTime()));
                } else if (colType == Types.BLOB) {
                    byte[] bytes = rs.getBytes(i);

                    if (bytes != null)
                        val = rs.getBytes(i).toString();

                    if (visitor != null) {
                        visitor.visitColumn(rs.getMetaData().getColumnName(i), colType, val);
                    }
                } else {
                    val = rs.getString(i);
                    if (visitor != null) {
                        visitor.visitColumn(rs.getMetaData().getColumnName(i), colType, val);
                    }
                }
                sb.append((val == null ? "" : escaper.translate(val)));
                if (i != nrOfColumns) {
                    sb.append(delimiter);
                } else {
                    sb.append("\n");
                }
            }
            writer.append(sb.toString());
        }
        writer.flush();
        return nrOfRows;
    }