    @Test
    public void detachView() throws Exception {
        mPresenter.create();
        assertThat(mPresenter.getView()).isNull();

        final TiView view = mock(TiView.class);
        mPresenter.attachView(view);
        assertThat(mPresenter.getView()).isEqualTo(view);

        mPresenter.detachView();
        assertThat(mPresenter.getView()).isNull();
    }