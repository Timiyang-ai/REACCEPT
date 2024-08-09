public void equiToNorm(float x , float y , Vector3D_F32 norm ) {
		equiToLatlon(x,y, temp);
		ConvertCoordinates3D_F32.latlonToUnitVector(temp.x,temp.y, norm);
	}