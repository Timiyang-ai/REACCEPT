public void equiToNorm(float x , float y , Vector3D_F32 norm ) {
		equiToLonlat(x,y, temp);
		ConvertCoordinates3D_F32.latlonToUnitVector(temp.y,temp.x, norm);
	}