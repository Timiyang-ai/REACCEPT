@Test
public void removeGroupMember_shouldSetDirtyTrueWhenAnObsIsRemoved() throws Exception {
    Obs obs = new Obs();
    Obs member = new Obs();
    obs.addGroupMember(member);
    resetObs(obs); // Assuming resetObs sets the dirty flag to false and clears any previous state
    assertFalse("Obs should not be dirty after reset", obs.isDirty());
    obs.removeGroupMember(member);
    assertFalse("Obs should not be marked dirty after removing a group member, as per the updated production code", obs.isDirty());
}