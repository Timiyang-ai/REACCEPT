@Nonnull
    String toScript(@Nonnull final TransformRequest request) {
        final StringBuilder script = new StringBuilder();
        script.append("class Transform (destination: String, policies: Array[com.thinkbiganalytics.policy.rest.model.FieldPolicy], validator: com.thinkbiganalytics.spark.datavalidator.DataValidator,"
                      + " profiler: com.thinkbiganalytics.spark.dataprofiler.Profiler, sqlContext: org.apache.spark.sql.SQLContext,"
                      + " sparkContextService: com.thinkbiganalytics.spark.SparkContextService) extends ");
        script.append(transformScriptClass.getName());
        script.append("(destination, policies, validator, profiler, sqlContext, sparkContextService) {\n");

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
        script.append("new Transform(tableName, policies, validator, profiler, sqlContext, sparkContextService).run()\n");

        return script.toString();
    }