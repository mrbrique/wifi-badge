package net.ddns.lnhc.wifibadge.util;

import org.junit.Assert;
import org.junit.Test;

public class QrVersionTest {

	@Test
	public void testGetModules() {
		QrVersion qrVersion = new QrVersion();

		Assert.assertEquals(21, qrVersion.getModules(1));

		Assert.assertEquals(61, qrVersion.getModules(11));

		Assert.assertEquals(101, qrVersion.getModules(21));

		Assert.assertEquals(141, qrVersion.getModules(31));

		Assert.assertEquals(101, qrVersion.getModules(-21));

	}

	@Test
	public void testFind() {
		QrVersion qrVersion = new QrVersion();

		Assert.assertEquals(15, qrVersion.find(77));

		Assert.assertEquals(16, qrVersion.find(78));

	}

}
