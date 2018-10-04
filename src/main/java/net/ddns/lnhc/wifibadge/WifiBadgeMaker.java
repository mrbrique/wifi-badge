package net.ddns.lnhc.wifibadge;

import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.ParseException;
import org.jfree.graphics2d.svg.SVGGraphics2D;

import com.google.zxing.common.BitMatrix;

import net.ddns.lnhc.qrmaze.QrMazeBuilder;
import net.ddns.lnhc.wifibadge.cli.OptionsFactory;
import net.ddns.lnhc.wifibadge.glyph.Glyph;
import net.ddns.lnhc.wifibadge.util.QrVersion;
import net.ddns.lnhc.wifibadge.util.SvgWriter;
import net.ddns.lnhc.wifibadge.wifi.WifiNetworkConfig;

public class WifiBadgeMaker {

	public static final String DEFAULT_FILENAME = "wifi-badge.svg";

	public static void main(String[] args) {

		CommandLine cmd = null;

		try {
			cmd = new DefaultParser().parse(OptionsFactory.build(), args, false);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		WifiBadgeMaker maker = new WifiBadgeMaker();
		SVGGraphics2D svg2d = maker.make(new WifiNetworkConfig(cmd.getOptionValue(OptionsFactory.ARGUMENT_SSID),
				cmd.getOptionValue(OptionsFactory.ARGUMENT_WPA)));

		List<String> argList = cmd.getArgList();
		String filename = null;
		if (!argList.isEmpty()) {
			filename = cmd.getArgList().get(0);
		}

		SvgWriter.write(svg2d, filename);
	}

	public SVGGraphics2D make(String ssid) {
		return make(new WifiNetworkConfig(ssid));
	}

	public SVGGraphics2D make(WifiNetworkConfig wifiConfig) {

		QrMazeBuilder builder = new WifiBadgeMazeBuilder();

		int module = Math.max(Glyph.getWidth(wifiConfig.getSsid(), null),
				Glyph.getWidth(wifiConfig.getPassword(), null));

		String wifiMeCard = wifiConfig.toMeCardForm();
		BitMatrix qrMatrix = builder.prepare(wifiMeCard,
				new QrVersion().find(module + 2 * WifiBadgeCanvas.BANNER_HORIZONTAL_MARGIN, wifiMeCard));

		return drawBadge(qrMatrix, wifiConfig);
	}

	private SVGGraphics2D drawBadge(BitMatrix qrMatrix, WifiNetworkConfig config) {
		return new WifiBadgeCanvas(qrMatrix, config).draw();
	}

}
