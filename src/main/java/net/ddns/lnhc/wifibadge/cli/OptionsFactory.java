package net.ddns.lnhc.wifibadge.cli;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

public final class OptionsFactory {

	public static final String ARGUMENT_SSID = "ssid";
	public static final String ARGUMENT_WPA = "wpa";

	public static Options build() {
		Options options = new Options();

		options.addOption(
				Option.builder(ARGUMENT_SSID).desc("SSID for the wfi badge").hasArg().argName("SSID").build());
		options.addOption(Option.builder(ARGUMENT_WPA).desc("Password for the wfi badge").hasArg().argName("WPA")
				.optionalArg(true).build());

		return options;
	}

}
