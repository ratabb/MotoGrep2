package org.illegaler.ratabb.motogrep.lollipop.mod;

import android.content.Context;
import android.content.Intent;

public abstract interface BroadcastSubReceiver {
	public abstract void onBroadcastReceived(Context context, Intent intent);
}
