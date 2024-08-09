	public void actionPerformed (ActionEvent e)
	{
		if (e.getSource() == bOK)
		{
			while (m_worker != null && m_worker.isAlive())
				m_worker.interrupt();
			dispose();
		}
	}   //  actionEvent