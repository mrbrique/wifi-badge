package net.ddns.lnhc.wifibadge.wifi;

import net.ddns.lnhc.wifibadge.Wpa2Generator;

public class WifiNetworkConfig {

	private static final String SPECIAL_CHARACTERS = "\\\":;,";

	public Authentication getAuthType() {
		return authType;
	}

	public void setAuthType(Authentication authType) {
		this.authType = authType;
	}

	public String getSsid() {
		return ssid;
	}

	public void setSsid(String ssid) {
		this.ssid = ssid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isHidden() {
		return hidden;
	}

	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}

	private Authentication authType;
	private String ssid;
	private String password;
	private boolean hidden;

	private WifiNetworkConfig() {
		this.hidden = false;
		this.authType = Authentication.WPA;
	}

	public WifiNetworkConfig(String ssid) {
		this(ssid, null);
	}

	public WifiNetworkConfig(String ssid, String password) {
		this();
		this.ssid = escape(ssid);
		this.password = (password == null) ? Wpa2Generator.generate(ssid) : escape(password);
	}

	private String escape(String context) {

		for (int i = 0; i < SPECIAL_CHARACTERS.length(); i++) {
			char sp = SPECIAL_CHARACTERS.charAt(i);
			context = context.replace(sp + "", "\\" + sp);
		}
		return context;
	}

	@Override
	public String toString() {
		return "WifiNetworkConfig [authType=" + authType + ", ssid=" + ssid + ", password=" + password + ", hidden="
				+ hidden + "]";
	}

	public String toMeCardForm() {
		return String.format("WIFI:S:%s;T:%s;P:%s;;", ssid, authType.name(), password);
	}

	public enum Authentication {
		WPA, WEP;
	}

}
