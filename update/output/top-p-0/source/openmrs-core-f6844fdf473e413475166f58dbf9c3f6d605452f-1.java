@Test(expected = APIException.class)
@Verifies(value = "should throw error when privilege is core privilege", method = "purgePrivilege(Privilege)")
public void purgePrivilege_shouldThrowErrorWhenPrivilegeIsCorePrivilege() throws Exception {
    // Replace "YOUR_PRIVILEGE_CONSTANT" with the actual privilege constant you intend to test
    String privilegeName = "YOUR_PRIVILEGE_CONSTANT"; // This should be the actual name of the privilege
    Privilege privilege = new Privilege(privilegeName);
    Context.getUserService().purgePrivilege(privilege);
}