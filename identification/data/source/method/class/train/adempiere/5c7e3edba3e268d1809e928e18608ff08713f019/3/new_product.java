private void jbInit() throws Exception
	{
		this.setName("Login");
		titleLabel.setFont(new java.awt.Font("Serif", 2, 10));
		titleLabel.setForeground(Color.blue);
		titleLabel.setRequestFocusEnabled(false);
		titleLabel.setToolTipText(Adempiere.getURL());
		titleLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		titleLabel.setIcon(Adempiere.getImageIconLogo());
		titleLabel.setText(Adempiere.getSubtitle());
		titleLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
		versionLabel.setRequestFocusEnabled(false);
		versionLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		versionLabel.setHorizontalTextPosition(SwingConstants.RIGHT);
		hostLabel.setRequestFocusEnabled(false);
		hostLabel.setLabelFor(hostField);
		hostField.addActionListener(this);
		userLabel.setRequestFocusEnabled(false);
		userLabel.setLabelFor(userTextField);
		passwordLabel.setRequestFocusEnabled(false);
		passwordLabel.setLabelFor(passwordField);
		languageLabel.setLabelFor(languageCombo);
		copy0Label.setFont(new java.awt.Font("Serif", 2, 10));
		copy0Label.setForeground(Color.blue);
		copy0Label.setRequestFocusEnabled(false);
		copy1Label.setRequestFocusEnabled(false);
		roleLabel.setRequestFocusEnabled(false);
		roleLabel.setLabelFor(roleCombo);
		clientLabel.setRequestFocusEnabled(false);
		orgLabel.setRequestFocusEnabled(false);
		dateLabel.setRequestFocusEnabled(false);
		warehouseLabel.setRequestFocusEnabled(false);
		printerLabel.setRequestFocusEnabled(false);
		compileDate.setHorizontalAlignment(SwingConstants.RIGHT);
		compileDate.setHorizontalTextPosition(SwingConstants.RIGHT);
		compileDate.setText(Adempiere.DATE_VERSION);
		compileDate.setToolTipText(Adempiere.getImplementationVendor());
		southPanel.setLayout(southLayout);
		loginTabPane.addChangeListener(this);

		//	ConnectionTab
		connectionPanel.setLayout(connectionLayout);
		//
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		versionLabel.setText(Adempiere.MAIN_VERSION);
		versionLabel.setToolTipText(Adempiere.getImplementationVersion());
		hostLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		hostLabel.setText("Host");
		hostLabel.setLabelFor(hostField);
		connectionPanel.add(hostLabel,         new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 12, 5, 5), 0, 0));
		connectionPanel.add(hostField,           new GridBagConstraints(1, 2, 3, 1, 1.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 5, 12), 0, 0));
		userLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		userLabel.setText("User");
		userLabel.setLabelFor(userTextField);
		connectionPanel.add(userLabel,        new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 12, 5, 5), 0, 0));
		userTextField.setText("System");			//	default
		connectionPanel.add(userTextField,         new GridBagConstraints(1, 3, 3, 1, 1.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 5, 12), 0, 0));
		passwordLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		passwordLabel.setText("Password");
		passwordLabel.setLabelFor(passwordField);
		connectionPanel.add(passwordLabel,         new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 12, 5, 5), 0, 0));
		passwordField.setText("System");			//	default
		connectionPanel.add(passwordField,         new GridBagConstraints(1, 4, 3, 1, 1.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 12), 0, 0));
		languageLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		languageLabel.setText("Language");
		languageLabel.setLabelFor(languageCombo);
		languageCombo.addActionListener(this);
		// @Trifon - begin
		connectionPanel.add(languageLabel,        new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 12, 5, 5), 0, 0));
		connectionPanel.add(languageCombo,          new GridBagConstraints(1, 5, 3, 1, 1.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 5, 12), 0, 0));
		// @Trifon - end
		copy0Label.setHorizontalAlignment(SwingConstants.RIGHT);
		connectionPanel.add(copy0Label,       new GridBagConstraints(0, 6, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));

		copy1Label.setText(Adempiere.COPYRIGHT);
		connectionPanel.add(copy1Label,         new GridBagConstraints(1, 6, 2, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 12, 12), 0, 0));
		connectionPanel.add(compileDate,        new GridBagConstraints(2, 1, 2, 1, 0.0, 0.0
			,GridBagConstraints.NORTHEAST, GridBagConstraints.NONE, new Insets(2, 0, 0, 12), 0, 0));
		connectionPanel.add(titleLabel,         new GridBagConstraints(0, 0, 2, 2, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(12, 12, 5, 5), 0, 0));
		connectionPanel.add(versionLabel,        new GridBagConstraints(2, 0, 2, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(12, 5, 0, 12), 0, 0));

		loginTabPane.add(connectionPanel, res.getString("Connection"));

		//	DefaultTab
		defaultPanel.setLayout(defaultPanelLayout);
		//
		roleLabel.setText("Role");
		roleLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		roleLabel.setLabelFor(roleCombo);
		roleCombo.addActionListener(this);
		defaultPanel.add(roleLabel,        new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(12, 12, 5, 5), 0, 0));
		defaultPanel.add(roleCombo,        new GridBagConstraints(1, 0, 1, 1, 1.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(12, 0, 5, 12), 0, 0));
		clientLabel.setText("Client");
		clientLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		clientLabel.setLabelFor(clientCombo);
		defaultPanel.add(clientLabel,       new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 12, 5, 5), 0, 0));
		clientCombo.addActionListener(this);
		defaultPanel.add(clientCombo,        new GridBagConstraints(1, 1, 1, 1, 1.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 5, 12), 0, 0));
		orgLabel.setText("Organization");
		orgLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		orgLabel.setLabelFor(orgCombo);
		defaultPanel.add(orgLabel,         new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 12, 5, 5), 0, 0));
		orgCombo.addActionListener(this);
		defaultPanel.add(orgCombo,        new GridBagConstraints(1, 2, 1, 1, 1.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 12), 0, 0));
		dateLabel.setText("Date");
		dateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		dateLabel.setLabelFor(dateField);
		defaultPanel.add(dateLabel,       new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 12, 5, 5), 0, 0));
		defaultPanel.add(dateField,        new GridBagConstraints(1, 4, 1, 1, 1.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 5, 12), 0, 0));
		//
		warehouseLabel.setText("Warehouse");
		warehouseLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		warehouseLabel.setLabelFor(warehouseCombo);
		printerLabel.setText("Printer");
		printerLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		printerLabel.setLabelFor(printerField);
		
		defaultPanel.add(printerLabel,        new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 12, 12, 5), 0, 0));
		defaultPanel.add(printerField,        new GridBagConstraints(1, 5, 1, 1, 1.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 12, 12), 0, 0));
		
		defaultPanel.add(warehouseLabel,  new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 12, 5, 5), 0, 0));
		defaultPanel.add(warehouseCombo,   new GridBagConstraints(1, 3, 1, 1, 1.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 5, 12), 0, 0));
		//
		loginTabPane.add(defaultPanel, res.getString("Defaults"));
		//
		this.getContentPane().add(mainPanel);
		mainPanel.add(loginTabPane, BorderLayout.CENTER);
		mainPanel.setName("loginMainPanel");
		mainPanel.add(southPanel, BorderLayout.SOUTH);
		//
		southPanel.add(confirmPanel, BorderLayout.NORTH);
		southPanel.add(statusBar, BorderLayout.SOUTH);
		//helpScollPane.getViewport().add(onlineHelp, null);
		confirmPanel.addActionListener(this);
		
		CButton helpBtn = new CButton(Env.getImageIcon2("Help24"));
		helpBtn.setActionCommand("onlineLoginHelp");
		helpBtn.addActionListener(this);
		helpBtn.setToolTipText(res.getString("Help"));
		//	Forgot password
		CButton resetPasswordBtn = new CButton(Env.getImageIcon2("LockX24"));
		resetPasswordBtn.setActionCommand("ResetPassword");
		resetPasswordBtn.addActionListener(this);
		
		confirmPanel.addComponent(helpBtn);
		confirmPanel.addComponent(resetPasswordBtn);
		
		statusBar.setStatusDB(null);
	}