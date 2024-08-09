@Nonnull
    @VisibleForTesting
    String toScript(@Nonnull final TransformRequest request) {
        final StringBuilder script = new StringBuilder();
        script.append("class Transform (sqlContext: org.apache.spark.sql.SQLContext, sparkContextService: com.thinkbiganalytics.spark.SparkContextService) extends ");
        script.append(transformScriptClass.getName());
        script.append("(sqlContext, sparkContextService) {\n");

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
        }

        script.append("}\n");
        script.append("new Transform(sqlContext, sparkContextService).run()\n");
        return script.toString();
    }