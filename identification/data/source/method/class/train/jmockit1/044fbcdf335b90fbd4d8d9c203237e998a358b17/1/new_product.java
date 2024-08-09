public void doBusinessOperationXyz() throws EmailException
   {
      // Locate existing persistent entities of the same entity type (note that the query string is a DSL for querying
      // persistent domain entities, written in terms of the domain, not in terms of relational tables and columns):
      List<EntityX> items = find("select item from EntityX item where item.someProperty=?1", data.getSomeProperty());

      // Compute or obtain from another service a total value for the new persistent entity:
      BigDecimal total = new BigDecimal("12.30");
      data.setTotal(total);

      // Persist the entity (no DAO required for such a common, high-level, operation):
      persist(data);

      sendNotificationEmail(items);
   }