    public void fromString() {
        ValueSet valueSet = ValueSet.fromString("1000:100,200:-1");

        // times
        assertThat(valueSet.getFadeTime(), is(1000));
        assertThat(valueSet.getHoldTime(), is(-1));

        // values
        assertThat(valueSet.getValue(0), is(100));
        assertThat(valueSet.getValue(1), is(200));
    }