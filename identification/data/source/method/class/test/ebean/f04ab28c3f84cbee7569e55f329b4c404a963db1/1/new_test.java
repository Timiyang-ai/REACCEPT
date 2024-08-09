  private Query<Customer> parse(String raw) {

    Query<Customer> query = DB.find(Customer.class);
    EqlParser.parse(raw, (SpiQuery<?>) query);
    return query;
  }