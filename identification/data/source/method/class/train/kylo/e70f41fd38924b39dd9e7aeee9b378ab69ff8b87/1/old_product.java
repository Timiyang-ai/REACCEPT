@Nonnull
    protected String toScript(@Nonnull final TransformRequest request) {
        StringBuilder script = new StringBuilder();
        script.append("class Transform (destination: String, sendResults: Boolean, sqlContext: org.apache.spark.sql.SQLContext)");
        script.append(" extends com.thinkbiganalytics.spark.metadata.TransformScript(destination, sendResults, sqlContext) {\n");

        script.append("override def dataFrame: org.apache.spark.sql.DataFrame = {");
        script.append(request.getScript());
        script.append("}\n");

        if (request.getParent() != null) {
            script.append("override def parentDataFrame: org.apache.spark.sql.DataFrame = {");
            script.append(request.getParent().getScript());
            script.append("}\n");
            script.append("override def parentTable: String = {\"");
            script.append(StringEscapeUtils.escapeJava(request.getParent().getTable()));
            script.append("\"}\n");

            this.cache.touch(request.getParent().getTable());
        }

        script.append("}\n");
        script.append("new Transform(tableName, true, sqlContext).run()\n");

        return script.toString();
    }