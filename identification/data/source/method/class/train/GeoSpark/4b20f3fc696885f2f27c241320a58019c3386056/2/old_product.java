public Envelope getMBR()
	{
		Envelope mbr=new Envelope(center.getX()-radius, center.getX()+radius, center.getY()-radius, center.getY()+radius);
		return mbr;
	}