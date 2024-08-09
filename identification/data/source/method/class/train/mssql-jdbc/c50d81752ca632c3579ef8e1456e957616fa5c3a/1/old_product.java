private void createTable(Connection connection, Statement stmt) throws SQLException {
        String createQuery = "CREATE TABLE " + tableName + " (" + "[Id] [int] IDENTITY(1,1) NOT NULL,"
                + "[CompanyName] [nvarchar](40) NOT NULL," + "[ContactName] [nvarchar](50) NULL,"
                + "[ContactTitle] [nvarchar](40) NULL," + "[City] [nvarchar](40) NULL,"
                + "[CountryName] [nvarchar](40) NULL,"
                + "[Phone] [nvarchar](30) MASKED WITH (FUNCTION = 'default()') NULL,"
                + "[Fax] [nvarchar](30) MASKED WITH (FUNCTION = 'default()') NULL)";
        stmt.execute(createQuery);

        stmt.execute("ADD SENSITIVITY CLASSIFICATION TO " + tableName
                + ".CompanyName WITH (LABEL='PII', LABEL_ID='L1', INFORMATION_TYPE='Company name', INFORMATION_TYPE_ID='COMPANY')");
        stmt.execute("ADD SENSITIVITY CLASSIFICATION TO " + tableName
                + ".ContactName WITH (LABEL='PII', LABEL_ID='L1', INFORMATION_TYPE='Person name', INFORMATION_TYPE_ID='NAME')");
        stmt.execute("ADD SENSITIVITY CLASSIFICATION TO " + tableName
                + ".Phone WITH (LABEL='PII', LABEL_ID='L1', INFORMATION_TYPE='Contact Information', INFORMATION_TYPE_ID='CONTACT')");
        stmt.execute("ADD SENSITIVITY CLASSIFICATION TO " + tableName
                + ".Fax WITH (LABEL='PII', LABEL_ID='L1', INFORMATION_TYPE='Contact Information', INFORMATION_TYPE_ID='CONTACT')");

        // INSERT ROWS OF DATA
        try (PreparedStatement ps = connection
                .prepareStatement("INSERT INTO " + tableName + " VALUES (?,?,?,?,?,?,?)")) {

            ps.setString(1, "Exotic Liquids");
            ps.setString(2, "Charlotte Cooper");
            ps.setObject(3, null);
            ps.setObject(4, "London");
            ps.setString(5, "UK");
            ps.setString(6, "(171) 555-2222");
            ps.setString(7, null);
            ps.execute();

            ps.setString(1, "New Orleans");
            ps.setString(2, "Cajun Delights");
            ps.setObject(3, null);
            ps.setObject(4, "New Orleans");
            ps.setString(5, "USA");
            ps.setString(6, "(100) 555-4822");
            ps.setString(7, null);
            ps.execute();

            ps.setString(1, "Grandma Kelly's Homestead");
            ps.setString(2, "Regina Murphy");
            ps.setObject(3, null);
            ps.setObject(4, "Ann Arbor");
            ps.setString(5, "USA");
            ps.setString(6, "(313) 555-5735");
            ps.setString(7, "(313) 555-3349");
            ps.execute();
        }
    }