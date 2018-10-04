package net.ddns.lnhc.wifibadge;

import net.ddns.lnhc.qrmaze.EncodeHintBuilder;
import net.ddns.lnhc.qrmaze.QrMazeBuilder;

public class WifiBadgeMazeBuilder extends QrMazeBuilder {

	@Override
	protected EncodeHintBuilder prepareEncodeHintBuilder(int qrVersion) {
		EncodeHintBuilder encodeHintBuilder = super.prepareEncodeHintBuilder(qrVersion);
		encodeHintBuilder.setMargin(0);
		return encodeHintBuilder;
	}

}
