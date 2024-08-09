public void printUsage() {
        StringBuilder builder = new StringBuilder();

        jCommander.usage(builder);

        builder.append(System.lineSeparator());
        builder.append("Go to http://schemaspy.org for a complete list/description of additional parameters.");
        builder.append(System.lineSeparator());
        builder.append(System.lineSeparator());
        builder.append("Sample usage using the default database type (implied -t ora):");
        builder.append(System.lineSeparator());
        builder.append(System.lineSeparator());
        builder.append(" java -jar schemaSpy.jar -db mydb -s myschema -u devuser -p password -o output");
        builder.append(System.lineSeparator());
        builder.append(System.lineSeparator());

        LOGGER.info(builder.toString());
    }