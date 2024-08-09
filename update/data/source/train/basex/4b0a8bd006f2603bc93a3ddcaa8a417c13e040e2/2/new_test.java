@Test
  public void union() {
    run("geo:union(<gml:Point><gml:coordinates>2</gml:coordinates></gml:Point>," +
            "<gml:Point><gml:coordinates>2,3</gml:coordinates></gml:Point>)",
            "<gml:MultiPoint xmlns:gml=\"http://www.opengis.net/gml\">" +
            "<gml:pointMember><gml:Point><gml:coordinates>2.0,0.0" +
            "</gml:coordinates></gml:Point></gml:pointMember><gml:pointMember>" +
            "<gml:Point><gml:coordinates>2.0,3.0</gml:coordinates></gml:Point>" +
            "</gml:pointMember></gml:MultiPoint>");

    run("geo:union(<gml:Point><gml:coordinates>2</gml:coordinates></gml:Point>," +
            "<gml:Point><gml:coordinates>3</gml:coordinates></gml:Point>)",
            "<gml:MultiPoint xmlns:gml=\"http://www.opengis.net/gml\">" +
            "<gml:pointMember><gml:Point>" +
            "<gml:coordinates>2.0,0.0</gml:coordinates></gml:Point></gml:pointMember>" +
            "<gml:pointMember><gml:Point><gml:coordinates>3.0,0.0</gml:coordinates>" +
            "</gml:Point></gml:pointMember></gml:MultiPoint>");

    error("geo:union(<gml:Point><gml:coordinates></gml:coordinates></gml:Point>," +
            "<gml:Point><gml:coordinates>2,3</gml:coordinates></gml:Point>)",
            GeoErrors.qname(2));
    error("geo:union(text {'a'}, <gml:Point><gml:coordinates>2,3" +
            "</gml:coordinates></gml:Point>)", FUNTYPE.qname());
  }