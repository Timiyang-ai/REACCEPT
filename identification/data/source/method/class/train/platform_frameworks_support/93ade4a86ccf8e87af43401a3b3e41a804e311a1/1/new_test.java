    @Test
    public void fullScroll_scrollsToEndOfMargin() {
        setup(300);
        setChildMargins(20, 30);
        mNestedScrollView.setSmoothScrollingEnabled(false);
        measureAndLayout(100);

        mNestedScrollView.fullScroll(View.FOCUS_DOWN);

        assertThat(mNestedScrollView.getScrollY(), is(250));
    }