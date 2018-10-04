package net.ddns.lnhc.wifibadge.wifi;

import org.junit.Assert;
import org.junit.Test;

public class WifiNetworkConfigTest {

	@Test
	public void testToMeCardForm() {

		Assert.assertEquals("WIFI:S:ss1d;T:WPA;P:wpa2;;", new WifiNetworkConfig("ss1d", "wpa2").toMeCardForm());

		Assert.assertEquals("WIFI:S:\\\"foo\\;bar\\\\baz\\\";T:WPA;P:wpa2;;",
				new WifiNetworkConfig("\"foo;bar\\baz\"", "wpa2").toMeCardForm());
	}
}
