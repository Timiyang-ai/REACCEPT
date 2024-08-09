	public void init() {
		System.out.println("*** init ***");

		setLayout(new BorderLayout());
		try {
			test = (Test) Class.forName(getParameter("test")).newInstance();
			Canvas canvas = (Canvas) test;
			canvas.setSize(getWidth(), getHeight());
			add(canvas);
		} catch (Exception e) {
			e.printStackTrace();
		}
		test.start();
	}