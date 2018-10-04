package net.ddns.lnhc.wifibadge.glyph;

import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class Glyph {

	private char character;

	private Path2D path;

	private List<Point2D.Float> points;

	public Glyph(char c, List<Point2D.Float> points) {
		this.character = c;
		this.points = points;
		this.newPath2D();
	}

	public char getCharacter() {
		return character;
	}

	public void setCharacter(char character) {
		this.character = character;
	}

	public List<Point2D.Float> getPoints() {
		return points;
	}

	public void setPoints(List<Point2D.Float> points) {
		this.points = points;
	}

	public void newPath2D() {
		Path2D.Float p2d = new Path2D.Float();

		for (Point2D.Float point : points) {
			p2d.moveTo(point.getX(), point.getY());
			p2d.lineTo(point.getX(), point.getY());
		}

		this.path = p2d;
	}

	public int getWidth() {
		return (int) (toPath2D().getBounds2D().getWidth() + 1);
	}

	public Path2D toPath2D() {
		return this.path;
	}

	public static int getWidth(String s, List<Glyph> glyphs) {
		int glyphWidth = 0;

		if (glyphs == null) {
			glyphs = new ArrayList<Glyph>();
		}

		for (int i = 0; i < s.length(); i++) {
			Glyph g = GlyphManager.get(s.charAt(i));
			glyphs.add(g);
			glyphWidth += g.getWidth();
		}

		// each glyphs has one pixel gap
		glyphWidth += glyphs.size();
		return glyphWidth - 1;

	}

}
