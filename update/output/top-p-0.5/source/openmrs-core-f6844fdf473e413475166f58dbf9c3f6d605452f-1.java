@Test(expected = APIException.class)
@Verifies(value = "should throw error when privilege is core privilege", method = "purgePrivilege(Privilege)")
public void purgePrivilege_shouldThrowErrorWhenPrivilegeIsCorePrivilege() throws Exception {
    // Note: Replace "YOUR_CORRECT_PRIVILEGE_CONSTANT_HERE" with the actual privilege constant you wish to test against.
    // Example: PrivilegeConstants.DELETE_USERS
    String privilegeName = "YOUR_CORRECT_PRIVILEGE_CONSTANT_HERE"; // This needs to be replaced with the actual constant
    Privilege privilege = new Privilege(privilegeName);
    Context.getUserService().purgePrivilege(privilege);
}