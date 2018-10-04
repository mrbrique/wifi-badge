package net.ddns.lnhc.wifibadge.util;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.TreeMap;

public class QrVersion {

	TreeMap<Integer, Integer> versionTree = new TreeMap<Integer, Integer>();

	TreeMap<Integer, Integer> databitTree = new TreeMap<Integer, Integer>();

	private static final String PROPERTIES_NAME = "databits.properties";

	private static final int MAX_VERSION = 40;

	private static final int BASE_MODULES = 21;

	private static final int STEPPING = 4;

	public QrVersion() {
		for (int i = 1; i <= MAX_VERSION; i++) {
			versionTree.put(getModules(i), i);
		}

		BufferedInputStream propStream = new BufferedInputStream(
				getClass().getClassLoader().getResourceAsStream(PROPERTIES_NAME));

		Properties databitProp = new Properties();
		try {
			databitProp.load(propStream);
			for (String versionName : databitProp.stringPropertyNames()) {
				databitTree.put(Integer.parseInt(databitProp.getProperty(versionName)), Integer.parseInt(versionName));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int find(int module) {
		return find(module, "");
	}

	public int find(int module, String context) {
		Entry<Integer, Integer> versionEntry = versionTree.ceilingEntry(module);

		Entry<Integer, Integer> databitEntry = databitTree.ceilingEntry(context.length());
		return Math.max(versionEntry.getValue(), databitEntry.getValue());

	}

	public int getModules(int version) {
		return ((Math.abs(version) - 1) * STEPPING + BASE_MODULES);
	}

}
