@Test
public void evalWithRestrictedPermissions() {
  // Testing with permission constraints
  // Adapted based on the provided JUnit failure message to specifically test DB_OPEN2_X error
  error(_XQUERY_EVAL.args("db:open(\"Sandbox\")", " map { }", " map { 'permission': 'none' }"), DB_OPEN2_X);
}