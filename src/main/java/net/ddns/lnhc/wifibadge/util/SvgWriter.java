package net.ddns.lnhc.wifibadge.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

import org.jfree.graphics2d.svg.SVGGraphics2D;

public class SvgWriter {

	public static final String DEFAULT_FILENAME = "wifi-badge.svg";

	public static void write(SVGGraphics2D svg2d, String filename) {
		if (filename == null) {
			filename = DEFAULT_FILENAME;
		}

		try {
			PrintWriter writer = new PrintWriter(
					new BufferedOutputStream(new FileOutputStream(new File(".\\", filename))));
			writer.print(svg2d.getSVGDocument());
			writer.flush();
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
