package net.ddns.lnhc.wifibadge.glyph;

import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

import com.google.gson.stream.JsonReader;

public class GlyphManager {

	private static final List<Glyph> GLYPHS = new Vector<Glyph>();

	private static final String GLYPH_FILE = "glyph.json";

	public static List<Glyph> getGlyphs() {
		readGlyph(GLYPH_FILE);
		return Collections.unmodifiableList(GLYPHS);
	}

	protected static void readGlyph(String glyphFile) {

		if (!GLYPHS.isEmpty()) {
			return;
		}

		JsonReader reader = new JsonReader(new BufferedReader(
				new InputStreamReader(GlyphManager.class.getClassLoader().getResourceAsStream(glyphFile))));

		try {
			reader.beginObject();
			while (reader.hasNext()) {
				String c = reader.nextName();
				reader.beginArray();

				// points
				List<Point2D.Float> points = new ArrayList<Point2D.Float>();
				while (reader.hasNext()) {
					// point object
					reader.beginObject();
					String x = reader.nextName();
					String y = reader.nextString();
					points.add(new Point2D.Float(Float.parseFloat(x), Float.parseFloat(y)));
					reader.endObject();
				}
				reader.endArray();
				Glyph g = new Glyph(c.charAt(0), points);
				GLYPHS.add(g);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Glyph get(Character c) {
		readGlyph(GLYPH_FILE);
		return GLYPHS.stream().filter(g -> g.getCharacter() == Character.toUpperCase(c)).findFirst().orElse(null);
	}

}
