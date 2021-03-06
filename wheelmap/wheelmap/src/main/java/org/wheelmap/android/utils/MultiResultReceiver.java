/*
 * #%L
 * Wheelmap - App
 * %%
 * Copyright (C) 2011 - 2012 Michal Harakal - Michael Kroez - Sozialhelden e.V.
 * %%
 * Wheelmap App based on the Wheelmap Service by Sozialhelden e.V.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *         http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS-IS" BASIS
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package org.wheelmap.android.utils;

import java.util.HashSet;
import java.util.Set;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.util.Log;

/**
 * Its important, that this {@link ResultReceiver} is intended to live at a
 * {@link Service} and a receiver is just registering there. So multiple
 * activities {@link Activity} can get info about the state of a single service.
 */
public class MultiResultReceiver extends ResultReceiver {
	private static final String TAG = "MultiResultReceiver";
	private Set<ResultReceiver> mReceivers;

	private int mResultCode;
	private Bundle mResultData;

	public MultiResultReceiver(Handler handler) {
		super(handler);
		mReceivers = new HashSet<ResultReceiver>();
	}

	public void clearReceiver() {
		mReceivers.clear();
	}

	public int getReceiverCount() {
		return mReceivers.size();
	}

	public boolean addReceiver(ResultReceiver receiver, boolean resendLast) {
		boolean isAdded = false;

		if (receiver == null)
			return false;
		if (!mReceivers.contains(receiver)) {
			mReceivers.add(receiver);
			isAdded = true;
		}

		if (resendLast)
			receiver.send(mResultCode, mResultData);

		return isAdded;
	}

	public void removeReceiver(ResultReceiver receiver) {
		mReceivers.remove(receiver);
	}

	@Override
	protected void onReceiveResult(int resultCode, Bundle resultData) {
		boolean sentOnce = false;

		mResultCode = resultCode;
		mResultData = resultData;

		for (ResultReceiver receiver : mReceivers) {
			receiver.send(resultCode, resultData);
			sentOnce = true;
		}

		if (!sentOnce)
			Log.w(TAG, "Dropping result on floor for code " + resultCode + ": "
					+ resultData.toString());
	}
}
