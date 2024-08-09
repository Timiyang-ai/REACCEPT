@Test(expected = APIException.class)
@Verifies(value = "should throw error when privilege is core privilege", method = "purgePrivilege(Privilege)")
public void purgePrivilege_shouldThrowErrorWhenPrivilegeIsCorePrivilege() throws Exception {
    // Assuming the privilege constant for the operation being tested has been correctly identified in your PrivilegeConstants class.
    // This reflects the pattern observed in the production method change.
    Context.getUserService().purgePrivilege(new Privilege(PrivilegeConstants.PURGE_PRIVILEGES));
}