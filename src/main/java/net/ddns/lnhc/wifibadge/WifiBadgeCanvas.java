package net.ddns.lnhc.wifibadge;

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import org.jfree.graphics2d.svg.SVGGraphics2D;
import org.jfree.graphics2d.svg.SVGHints;

import com.google.zxing.common.BitMatrix;

import net.ddns.lnhc.wifibadge.glyph.Glyph;
import net.ddns.lnhc.wifibadge.wifi.WifiNetworkConfig;

public class WifiBadgeCanvas {

	private static final int BLOCK_L_LENGTH = 9;
	private static final int BLOCK_B_LENGTH = 8;

	private static final int BLOCK_Q_BORDER = 1;

	private static final int CHAR_SPACE = 1;

	public static final int BANNER_HORIZONTAL_MARGIN = 2;
	public static final int BANNER_VERTICAL_MARGIN = 2;

	private static final int CHAR_HEIGHT = 5;

	private BitMatrix wifiBitmatrix;

	private WifiNetworkConfig wifiNetworkConfig;

	public WifiBadgeCanvas(BitMatrix matrix, WifiNetworkConfig wifiCfg) {
		this.wifiBitmatrix = matrix;
		this.wifiNetworkConfig = wifiCfg;
	}

	public SVGGraphics2D draw() {
		SVGGraphics2D g2d = new SVGGraphics2D(getQWidth() + BLOCK_L_LENGTH + BLOCK_Q_BORDER,
				getQHeight() + BLOCK_L_LENGTH + BLOCK_Q_BORDER);

		drawBlockS(g2d);
		drawBlockQ(g2d);
		drawBlockB(g2d);
		drawBlockW(g2d);
		return g2d;
	}

	protected void drawBlockS(SVGGraphics2D g2d) {
		int sWidth = getQWidth();
		int sHeight = BLOCK_L_LENGTH;
		g2d.setColor(Color.BLACK);
		// start group s
		g2d.setRenderingHint(SVGHints.KEY_BEGIN_GROUP, "s");

		g2d.fillRect(0, 0, sWidth, sHeight);
		// draw glyph of ssid
		String ssid = wifiNetworkConfig.getSsid();

		List<Glyph> ssidGlyphs = new ArrayList<Glyph>();
		int glyphWidth = Glyph.getWidth(ssid, ssidGlyphs);

		g2d.setColor(Color.WHITE);

		AffineTransform translate = AffineTransform.getTranslateInstance((getQWidth() - glyphWidth) / 2,
				BANNER_VERTICAL_MARGIN);

		// start group ssid
		g2d.setRenderingHint(SVGHints.KEY_BEGIN_GROUP, "ssid");
		drawGlyph(g2d, ssidGlyphs, translate);
		// end group ssid
		g2d.setRenderingHint(SVGHints.KEY_END_GROUP, null);
		// end group s
		g2d.setRenderingHint(SVGHints.KEY_END_GROUP, null);

	}

	protected void drawBlockB(SVGGraphics2D g2d) {
		g2d.setRenderingHint(SVGHints.KEY_BEGIN_GROUP, "b");
		g2d.setColor(Color.BLACK);
		// outer black square
		g2d.fillRect(getQWidth() + 1, 1, BLOCK_B_LENGTH, BLOCK_B_LENGTH);
		g2d.setColor(Color.WHITE);
		// inner white square
		g2d.fillRect(getQWidth() + 2, 2, BLOCK_B_LENGTH - 2, BLOCK_B_LENGTH - 2);
		g2d.setColor(Color.BLACK);
		// inner black square
		g2d.fillRect(getQWidth() + 3, 5, 2, 2);
		g2d.setRenderingHint(SVGHints.KEY_END_GROUP, null);
	}

	protected void drawBlockW(SVGGraphics2D g2d) {
		int wHeight = getQHeight();
		int wWidth = BLOCK_L_LENGTH;
		g2d.setColor(Color.BLACK);
		// start group w
		g2d.setRenderingHint(SVGHints.KEY_BEGIN_GROUP, "w");

		// draw banner
		int bannerY = BLOCK_L_LENGTH + BLOCK_Q_BORDER;
		g2d.fillRect(getQWidth() + BLOCK_Q_BORDER, bannerY, wWidth, wHeight);

		// draw wpa
		g2d.setColor(Color.WHITE);

		String wpa = wifiNetworkConfig.getPassword();
		List<Glyph> wpaGlyphs = new ArrayList<Glyph>();
		int glyphWidth = Glyph.getWidth(wpa, wpaGlyphs);

		int anchorX = getQWidth() + BLOCK_Q_BORDER + BANNER_VERTICAL_MARGIN;
		int anchorY = bannerY + ((getQHeight() - glyphWidth) / 2) - CHAR_HEIGHT;

		AffineTransform translate = AffineTransform.getTranslateInstance(anchorX, anchorY);

		g2d.setTransform(AffineTransform.getQuadrantRotateInstance(1, anchorX, anchorY + CHAR_HEIGHT));

		// start group wpa
		g2d.setRenderingHint(SVGHints.KEY_BEGIN_GROUP, "wpa");
		drawGlyph(g2d, wpaGlyphs, translate);
		// end group wpa
		g2d.setRenderingHint(SVGHints.KEY_END_GROUP, null);
		// end group w
		g2d.setRenderingHint(SVGHints.KEY_END_GROUP, null);

	}

	protected void drawBlockQ(SVGGraphics2D g2d) {
		g2d.setColor(Color.BLACK);

		AffineTransform transform = AffineTransform.getTranslateInstance(0, BLOCK_L_LENGTH + BLOCK_Q_BORDER);

		g2d = (SVGGraphics2D) g2d.create();
		g2d.setRenderingHint(SVGHints.KEY_BEGIN_GROUP, "q");
		g2d.setTransform(transform);

		for (int w = 0; w < wifiBitmatrix.getWidth(); w++) {
			for (int h = 0; h < wifiBitmatrix.getHeight(); h++) {
				if (wifiBitmatrix.get(w, h)) {
					// draw a dot
					g2d.fillRect(w, h, 1, 1);
				}
			}
		}
		g2d.setRenderingHint(SVGHints.KEY_END_GROUP, null);
		g2d.dispose();
	}

	protected void drawGlyph(SVGGraphics2D g2d, List<Glyph> glyphs, AffineTransform translate) {

		for (Glyph glyph : glyphs) {

			for (Point2D.Float point : glyph.getPoints()) {
				Rectangle2D rect = new Rectangle2D.Float((int) point.getX(), (int) point.getY(), 1, 1);
				g2d.fill(translate.createTransformedShape(rect));
			}

			Shape glyphShape = translate.createTransformedShape(glyph.toPath2D());
			// move anchor
			translate.translate(glyphShape.getBounds2D().getWidth() + CHAR_SPACE + 1, 0);
		}

	}

	public int getQWidth() {
		return wifiBitmatrix.getWidth();
	}

	public int getQHeight() {
		return wifiBitmatrix.getHeight();
	}

}
