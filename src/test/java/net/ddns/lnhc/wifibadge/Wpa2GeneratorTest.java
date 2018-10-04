package net.ddns.lnhc.wifibadge;

import org.junit.Assert;
import org.junit.Test;

public class Wpa2GeneratorTest {

	@Test
	public void test() {

		String wpa = "";

		wpa = Wpa2Generator.generate(4);
		Assert.assertEquals(wpa.length(), 4);
		System.out.println(wpa);

		wpa = Wpa2Generator.generate(wpa);
		System.out.println(wpa);

		wpa = Wpa2Generator.generate(6);
		Assert.assertEquals(wpa.length(), 6);
		System.out.println(wpa);

		wpa = Wpa2Generator.generate(wpa);
		System.out.println(wpa);

		wpa = Wpa2Generator.generate(8);
		Assert.assertEquals(wpa.length(), 8);
		System.out.println(wpa);

		wpa = Wpa2Generator.generate(wpa);
		System.out.println(wpa);

		wpa = Wpa2Generator.generate(12);
		Assert.assertEquals(wpa.length(), 12);
		System.out.println(wpa);

		wpa = Wpa2Generator.generate(wpa);
		System.out.println(wpa);

	}
}
