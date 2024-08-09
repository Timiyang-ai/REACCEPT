    @Test
    public void addListeners_shouldAddMockObjectListeners() {
        //when
        mockSettingsImpl.invocationListeners(invocationListener);
        mockSettingsImpl.stubbingLookupListeners(stubbingLookupListener);

        //then
        assertThat(mockSettingsImpl.getInvocationListeners()).contains(invocationListener);
        assertThat(mockSettingsImpl.getStubbingLookupListeners()).contains(stubbingLookupListener);
    }