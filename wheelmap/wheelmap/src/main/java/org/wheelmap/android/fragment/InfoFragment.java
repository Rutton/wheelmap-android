package org.wheelmap.android.fragment;

import java.util.ArrayList;

import org.wheelmap.android.online.R;
import org.wheelmap.android.ui.info.Info;
import org.wheelmap.android.ui.info.InfoTypes;
import org.wheelmap.android.ui.info.InfoWidgetsAdapter;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockListFragment;

public class InfoFragment extends SherlockListFragment {
	ArrayList<Info> infoList = new ArrayList<Info>();

	private OnInfoFragmentListener mListener;

	public interface OnInfoFragmentListener {
		public void onNextView(String view);

		public void onViewUri(Uri uri);
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		if (activity instanceof OnInfoFragmentListener)
			mListener = (OnInfoFragmentListener) activity;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Info info = null;
		// web version
		info = new Info(R.string.info_web_version,
				R.string.info_web_version_one, "http://www.wheelmap.org",
				InfoTypes.SIMPLE_TEXT);
		infoList.add(info);

		// mapdata
		info = new Info(R.string.info_kartendaten,
				R.string.info_kartendaten_one, R.string.info_kartendaten_two,
				"http://www.openstreetmap.org", InfoTypes.DOUBLE_TEXT);
		infoList.add(info);

		// android version
		info = new Info(R.string.info_android_development,
				R.string.info_android_development_one, "http://fiwio.com",
				R.string.info_android_development_two,
				"http://studiorutton.de", InfoTypes.WITH_TWO_LINKS);
		infoList.add(info);

		info = new Info(R.string.info_clientdevelopment,
				R.string.info_clientdevelopment_one, "", InfoTypes.SIMPLE_TEXT);
		infoList.add(info);

		// web development
		info = new Info(R.string.info_webdevelopment,
				R.string.info_webdevelopment_one,
				"http://www.christophbuente.de", InfoTypes.SIMPLE_TEXT);
		infoList.add(info);
		// legal notice
		info = new Info(R.string.btn_legal_notice, "LegalNoticeActivity",
				InfoTypes.NEXT_ACTIVITY);
		infoList.add(info);

		// project by sozialhelden
		info = new Info(R.string.info_a_project_of,
				R.drawable.logo_sozialhelden_232x47,
				"http://www.sozialhelden.de", InfoTypes.WITH_IMAGE);
		infoList.add(info);
		// thanks stiftung
		info = new Info(R.string.stiftung_text_one, R.drawable.logo_fds,
				"http://www.fdst.de/", InfoTypes.WITH_IMAGE);
		infoList.add(info);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_info, container, false);

		InfoWidgetsAdapter infoAdapter = new InfoWidgetsAdapter(getActivity(),
				infoList);
		setListAdapter(infoAdapter);

		return v;
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		Info info = (Info) this.getListAdapter().getItem(position);
		switch (info.getInfoType()) {
		case NEXT_ACTIVITY:
			if (mListener != null)
				mListener.onNextView(info.getNextView());
			break;
		default:

			if (mListener != null)
				mListener.onViewUri(Uri.parse(info.getUrl()));
		}
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onStart() {
		super.onStart();
	}

	@Override
	public void onResume() {
		super.onResume();
	}

}