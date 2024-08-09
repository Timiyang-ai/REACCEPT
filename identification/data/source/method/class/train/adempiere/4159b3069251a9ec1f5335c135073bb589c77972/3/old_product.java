public void actionPerformed(ActionEvent e)
	{
		if (e.getActionCommand().equals(ConfirmPanel.A_OK))
		{
			if (loginTabPane.getSelectedIndex() == 0)
				connectionOK();		//	first ok
			else
			{
				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				m_okPressed = true; 
				// Dispose if OK - teo_sarca [ 1674663 ]
				if(!defaultsOK())
					m_okPressed = false;
				setCursor(Cursor.getDefaultCursor());
			}
		}
		else if (e.getActionCommand().equals(ConfirmPanel.A_CANCEL))
			appExit();
		//
		else if (e.getSource() == hostField) 
			validateConnection();
		else if (e.getSource() == languageCombo)
			languageComboChanged();
		//
		else if (e.getSource() == roleCombo)
			roleComboChanged();
		else if (e.getSource() == clientCombo)
			clientComboChanged();
		else if (e.getSource() == orgCombo)
			orgComboChanged();
		else if ("onlineLoginHelp".equals(e.getActionCommand()))
			OnlineHelp.openInDefaultBrowser();
	}