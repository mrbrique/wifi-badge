package net.ddns.lnhc.wifibadge;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import net.ddns.lnhc.wifibadge.glyph.Glyph;
import net.ddns.lnhc.wifibadge.glyph.GlyphManager;

public class Wpa2Generator {

	private static final String WPA_CANDIDATE = "12346789ABCDEFGHIJKLMNPQRTUVWXYZ";

	private static final List<Glyph> WPA_GLYPHS = new ArrayList<Glyph>() {
		{
			for (int i = 0; i < WPA_CANDIDATE.length(); i++) {
				add(GlyphManager.get(WPA_CANDIDATE.charAt(i)));
			}
		}
	};

	// generate wpa sequence base on input size
	public static String generate(int size) {

		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < size; i++) {
			buffer.append(WPA_CANDIDATE.charAt((int) ((Math.random() * WPA_CANDIDATE.length()))));
		}
		return buffer.toString();
	}

	// generate wpa sequence base on input glyph width
	public static String generate(String s) {
		int inputWidth = Glyph.getWidth(s, null);

		List<Glyph> wpa = new ArrayList<Glyph>();
		generate(wpa, inputWidth);

		StringBuffer buffer = new StringBuffer();

		wpa.stream().forEach(g -> buffer.append(g.getCharacter()));

		return buffer.toString();
	}

	private static int generate(List<Glyph> glyphs, int r) {
		if (r == 0) {
			return 0;
		} else {
			List<Glyph> glyphChoice = WPA_GLYPHS.stream().filter(g -> g.getWidth() <= r).collect(Collectors.toList());

			if (glyphChoice.isEmpty()) {
				return r;
			} else {
				Glyph g = glyphChoice.get((int) (Math.random() * glyphChoice.size()));
				glyphs.add(g);
				return generate(glyphs, r - g.getWidth() - 1);
			}
		}
	}

}
