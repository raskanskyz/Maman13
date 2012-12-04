public class RGBImage {

	private RGBColor[][] pixels;

	/* OKAY */public RGBImage(int rows, int cols) {
		pixels = setArrayBlack(new RGBColor[rows][cols]);
	}// CTOR1

	/* OKAY */public RGBImage(RGBColor[][] pixels) {
		setPixelArray(pixels);
	}// CTOR2

	public RGBImage(RGBImage other) {
		this.pixels = copyArray(other.toRGBColorArray());
	}// CTOR3

	/******************** METHODS BELOW *******************************/
	public int getHeight() {
		return pixels.length;
	}// getHeight

	public int getWidth() {
		return pixels[0].length;
	}// getWidth

	public RGBColor getPixel(int row, int col) {
		if (row > getHeight() || col > getWidth()) {
			return new RGBColor();
		} else {
			return new RGBColor(pixels[row][col]);
		}

	}// getPixel

	public void setPixel(int row, int col, RGBColor pixel) {
		if (row > getHeight() || col > getWidth()) {
			return;
		}// if
		else {
			pixels[row][col] = new RGBColor(pixel);
		}// else

	}// setPixel

	public boolean equals(RGBImage other) {

		if (!(getHeight() == other.getHeight())
				|| !(getWidth() == other.getWidth())) {
			return false;
		}// if
		for (int i = 0; i < getHeight(); i++) {

			for (int j = 0; j < getWidth(); j++) {

				if (!pixels[i][j].equals(other.toRGBColorArray())) {
					return false;
				}// inner if

			}// inner loop

		}// outer loop
		return true;
	}// equals

	public void flipHorizontal() {

		RGBColor[][] flippedImage = new RGBColor[getHeight()][getWidth()];

		for (int i = 0; i < flippedImage.length; i++) {

			flippedImage[i] = pixels[getHeight() - i - 1];

		}// outer loop

		setPixelArray(flippedImage);
	}// flipHorizontal

	public void flipVertical() {

		RGBColor[][] flippedImage = copyArray(toRGBColorArray());

		for (int i = 0; i < flippedImage.length; i++) {

			for (int j = 0; j < flippedImage[0].length; j++) {
				flippedImage[i][j] = new RGBColor(pixels[i][getWidth() - 1 - j]);
			}// inner loop

		}// outer loop

		setPixelArray(flippedImage);
	}// flipVertical

	public void invertColors() {
		for (int i = 0; i < pixels.length; i++) {
			for (int j = 0; j < pixels[0].length; j++) {
				pixels[i][j].invert();
			}// inner loop
		}// outer loop
	}

	public RGBColor[][] toRGBColorArray() {
		return copyArray(copyArray(pixels));
	}// toRGBColorArray

	public void rotateClockwise() {

		RGBColor[][] rotatedArray = new RGBColor[getWidth()][getHeight()];

		for (int i = 0; i < rotatedArray.length; i++) {

			for (int j = 0; j < rotatedArray[0].length; j++) {

				rotatedArray[i][j] = toRGBColorArray()[getHeight() - 1 - j][i];

			}// inner loop

		}// outer loop
		setPixelArray(rotatedArray);
	}// rotateClockwise

	public void rotateCounterClockwise() {
		rotateClockwise();
		rotateClockwise();
		rotateClockwise();
	}// rotateCounterClockwise

	public void shiftCol(int offset) {
		if (offset == getWidth() || offset == -getWidth()) {
			setPixelArray(setArrayBlack(pixels));
		}// if equal to columns

		else if (offset > getWidth() - 1 || offset < -getWidth() + 1
				|| offset == 0) {
			return;
		}// if out of bounds

		else {

			RGBColor[][] blackSheet = setArrayBlack(pixels);
			if (offset > 0) {
				for (int i = 0; i < blackSheet.length; i++) {
					for (int j = 0; j < blackSheet[0].length; j++) {
						if (!(j < offset)) {
							blackSheet[i][j] = new RGBColor(
									toRGBColorArray()[i][j - offset]);
						}// if

					}// inner loop
				}// outer loop

				setPixelArray(blackSheet);
			}// if > 0

			else if (offset < 0) {
				flipVertical();
				offset = -offset;
				for (int i = 0; i < blackSheet.length; i++) {
					for (int j = 0; j < blackSheet[0].length; j++) {
						if (!(j < offset)) {
							blackSheet[i][j] = toRGBColorArray()[i][j - offset];
						}// if
					}// inner loop
				}// outer loop
				setPixelArray(blackSheet);
				flipVertical();
			}// else if
		}// else

	}// shiftCol

	public void shiftRow(int offset) {
		if (offset == getWidth() || offset == -getWidth()) {
			setPixelArray(setArrayBlack(pixels));
		}// if
		else {
			if (offset > 0) {

			}// if offset positive
			else if (offset < 0) {
				rotateClockwise();
				shiftCol(-offset);
				rotateCounterClockwise();
			}// of offset negative
		}// else
	}// shiftRow

	public double[][] toGrayscaleArray() {
		double[][] grayScaledArray = new double[getHeight()][getWidth()];
		for (int i = 0; i < pixels.length; i++) {
			for (int j = 0; j < pixels[0].length; j++) {

				grayScaledArray[i][j] = pixels[i][j].convertToGrayscale();
			}
		}
		return grayScaledArray;
	}// toGrayscaleArray

	public String toString() {
		String output = "";
		for (int i = 0; i < pixels.length; i++) {
			for (int j = 0; j < pixels[0].length; j++) {
				if (j != pixels[0].length - 1) {
					output += pixels[i][j].toString() + " ";
				} else {
					output += pixels[i][j].toString() + "\n";

				}
			}// inner loop

		}// outer loop
		return output;
	}// toString

	private RGBColor[][] copyArray(RGBColor[][] pixels) {
		RGBColor[][] copiedArray = new RGBColor[pixels.length][pixels[0].length];
		for (int i = 0; i < pixels.length; i++) {
			for (int j = 0; j < pixels[0].length; j++) {

				copiedArray[i][j] = new RGBColor(pixels[i][j]);

			}// inner loop
		}// outer loop
		return copiedArray;
	}// copyArray

	private void setPixelArray(RGBColor[][] pixelArray) {
		this.pixels = copyArray(pixelArray);
	}// getRGBImage

	private RGBColor[][] setArrayBlack(RGBColor[][] sourceArray) {
		RGBColor[][] blackArray = new RGBColor[sourceArray.length][sourceArray[0].length];
		for (int i = 0; i < blackArray.length; i++) {
			for (int j = 0; j < blackArray[0].length; j++) {
				blackArray[i][j] = new RGBColor();

			}// inner loop
		}// outer loop
		return blackArray;
	}// setArrayBlack

}// class
