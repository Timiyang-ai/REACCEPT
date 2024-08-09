@Test
public void removeGroupMember_shouldSetDirtyWhenAnObsIsRemoved() throws Exception {
    Obs obs = new Obs(2);
    Obs member = new Obs();
    obs.addGroupMember(member);
    // Confirming that adding a group member sets the Obs as dirty
    assertTrue("Obs should be dirty after adding a group member", obs.isDirty());
    resetObs(obs); // Assuming resetObs effectively resets the dirty flag to false
    // Ensure the Obs is not dirty after reset, preparing for the next operation
    assertFalse("Obs should not be dirty after reset", obs.isDirty());
    obs.removeGroupMember(member);
    // Correcting the expectation: Removing a group member should indeed set the Obs as dirty
    assertTrue("Obs should be dirty after removing a group member", obs.isDirty());
}