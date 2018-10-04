package net.ddns.lnhc.wifibadge;

import org.jfree.graphics2d.svg.SVGGraphics2D;
import org.junit.Test;

import net.ddns.lnhc.wifibadge.util.SvgWriter;

public class WifiBadgeMakerTest {

	@Test
	public void testMake() {

		WifiBadgeMaker maker = new WifiBadgeMaker();
		SVGGraphics2D svg2d = maker.make("Wifi.Badge-Maker_TEST");
		SvgWriter.write(svg2d, "wifi_badge_maker-test.svg");
	}

	@Test
	public void testMain() {
		WifiBadgeMaker.main("-ssid jsut-ssid".split(" "));
	}

}
