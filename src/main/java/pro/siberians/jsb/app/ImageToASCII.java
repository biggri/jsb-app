package pro.siberians.jsb.app;

import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.ByteArrayInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class ImageToASCII {

	public static final char[] symbols = new char[] {' ', '░' , '▒', '▓', '█'};
	
	public static char getImageParam(BufferedImage bi) {
		
		long total = 0;
		long count = 0;
		
		for(int i = 0; i < bi.getHeight(); i++) {
			for(int j = 0; j < bi.getWidth(); j++) {
				count += 16777216;
				total += bi.getRGB(i, j) + 16777216;
			}
		}
		
		int index = (int)((total / (count + 0.0) - 0.01) * symbols.length);
		
		return symbols[symbols.length - 1 - index];
	}
	
	public static List<String> getImage(byte[] bytes) throws Exception {
		BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(bytes));
		ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
		ColorConvertOp op = new ColorConvertOp(cs, null);
		BufferedImage image = op.filter(bufferedImage, null);
	
		int sideLength = 3;

		int width = image.getWidth() / sideLength;
		int height = image.getHeight() / sideLength;

		ArrayList<String> lines = new ArrayList<>();
		for(int i = 0; i < height; i++) {
			String response = "";
			for(int j = 0; j < width; j++) {
				int y = i * sideLength;
				int x = j * sideLength;
				BufferedImage subImage = image.getSubimage(x, y, sideLength, sideLength);
				char h = getImageParam(subImage);
				response = response + h;
			}
			System.out.println(response);
			lines.add(response);
		}
		return lines;
	}
	
	public static void main(String[] args) throws Exception {
		String fileName = "C:\\Users\\dns\\Pictures\\Irina.png";
		getImage(Files.readAllBytes(Paths.get(fileName)));
	}

}
