    @Test
    public void pageScroll_takesAccountOfMargin() {
        setup(75);
        setChildMargins(20, 30);
        mNestedScrollView.setSmoothScrollingEnabled(false);
        measureAndLayout(100);

        mNestedScrollView.pageScroll(View.FOCUS_DOWN);

        assertThat(mNestedScrollView.getScrollY(), is(25));
    }