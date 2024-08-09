public boolean contains(Point point)
	{
		if(this.center.distance(point)<this.radius)
		{
			return true;
		}
		else
		{
			return false;
		}
	}