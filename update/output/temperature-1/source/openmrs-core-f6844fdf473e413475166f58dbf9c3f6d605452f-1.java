@Test(expected = APIException.class)
public void purgePrivilege_shouldThrowErrorWhenPrivilegeIsCorePrivilege() throws Exception {
    Context.getUserService().purgePrivilege(new Privilege(PrivilegeConstants.PRIV_ADD_COHORTS));
}
//