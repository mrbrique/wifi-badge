package net.ddns.lnhc.wifibadge.glyph;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;

import org.junit.Test;

public class GlyphTest {

	@Test
	public void testGlyph() {

		GlyphManager.readGlyph("glyph.json");

		// prepare image sized 40 by 40
		BufferedImage img = new BufferedImage(48, 48, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = img.createGraphics();
		// draw white background
		g2d.fillRect(0, 0, img.getWidth(), img.getHeight());
		// set pen color
		g2d.setColor(Color.BLACK);

		int col = 1;
		// a low contains 4 characters
		int maxCol = 6;

		List<Glyph> glyphList = GlyphManager.getGlyphs();

		AffineTransform transformer = AffineTransform.getTranslateInstance(0, 0);

		for (Glyph glyph : glyphList) {

			Path2D glyph2d = glyph.toPath2D();
			Rectangle2D bound = glyph2d.getBounds2D();

			g2d.draw(transformer.createTransformedShape(glyph2d));

			transformer.translate(bound.getWidth() + 2, 0);

			if (col % maxCol == 0) {
				// reset to new line
				transformer.translate(-transformer.getTranslateX(), 6);
			}
			col++;
		}
		try {
			ImageIO.write(img, "bmp", new FileOutputStream("test_glyph.bmp"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
