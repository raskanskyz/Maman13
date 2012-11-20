public class RGBImage {

	private RGBColor[][] pixels;
	private int height;
	private int width;

	// ------------------------------------------------------------------------------
	public RGBImage(int rows, int cols) {
		pixels = new RGBColor[rows][cols];

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				pixels[i][j] = new RGBColor();
			}// inner loop
		}// outer loop
	}// CTOR1

	public RGBImage(RGBColor[][] pixels) { // ---C-O-N-S-T-R-U-C-T-O-R-S----
		setRGBImage(pixels);
	}// CTOR2

	public RGBImage(RGBImage other) {
		RGBImage image = new RGBImage(other.getRGBImage());
		setRGBImage(image.getRGBImage());
	}// CTOR3
		// -----------------------------------------------------------------------------

	/******************** METHODS BELOW *******************************/
	public int getHeight() {
		return height;
	}// getHeight

	public int getWidth() {
		return width;
	}// getWidth

	public RGBColor getPixel(int row, int col) {
		if (row > getHeight() || col > getWidth()) {
			return new RGBColor();
		} else {
			return pixels[row][col];
		}

	}// getPixel

	public void setPixel(int row, int col, RGBColor pixel) {
		if (row > getHeight() || col > getWidth()) {
			return;
		}// if
		else {
			pixels[row][col] = pixel;
			// should i do this instead--->pixels[row][col]= new
			// RGBColor(pixel);
		}// else

	}// setPixel

	public boolean equals(RGBImage other) {
		return true;
	}// equals

	public void setRGBImage(RGBColor[][] pixels) {
		this.pixels = pixels;
	}// setRGBImage

	public RGBColor[][] getRGBImage() {
		return pixels;
	}// getRGBImage

}// class
