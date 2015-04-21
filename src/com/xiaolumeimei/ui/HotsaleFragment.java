package com.xiaolumeimei.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xiaolumeimei.common.adapter.MyListAdspter;
import com.xiaolumeimei.common.adapter.MyListAdspterofAll;
import com.xiaolumeimei.common.view.ViewPagerIndicatorView;
import com.xiaolumeimei.yann.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class HotsaleFragment extends Fragment {
	private ViewPagerIndicatorView viewPagerIndicatorView;
	private ListView listView = null, listView2 = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.hottoday_fragment, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		View v1 = LayoutInflater.from(getActivity().getApplicationContext())
				.inflate(R.layout.hotsalefragment1, null);
		View v2 = LayoutInflater.from(getActivity().getApplicationContext())
				.inflate(R.layout.hotsalefragment2, null);
		listView = (ListView) v1.findViewById(R.id.yesterdaylistview);
		listView2 = (ListView) v2.findViewById(R.id.list2);
		List<Map<String, Object>> list = getData();
		listView.setAdapter(new MyListAdspter(getActivity()
				.getApplicationContext(), list));
		List<Map<String, Object>> list1 = getData1();
		listView2.setAdapter(new MyListAdspterofAll(getActivity()
				.getApplicationContext(), list1));

		this.viewPagerIndicatorView = (ViewPagerIndicatorView) getView()
				.findViewById(R.id.viewpager_indicator_view);
		final Map<String, View> map = new HashMap<String, View>();

		map.put(getString(R.string.yesterdayhot), v1);
		map.put(getString(R.string.onlyoneday),
				LayoutInflater.from(getActivity().getApplicationContext())
						.inflate(R.layout.todayhot, null));
		map.put(getString(R.string.todaynewthing), v2);
		map.put(getString(R.string.childernthing),
				LayoutInflater.from(getActivity().getApplicationContext())
						.inflate(R.layout.todayhot, null));
		map.put(getString(R.string.motherthing),
				LayoutInflater.from(getActivity().getApplicationContext())
						.inflate(R.layout.todayhot, null));
		this.viewPagerIndicatorView.setupLayout(map);
	}

	public List<Map<String, Object>> getData() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < 10; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("image", R.drawable.shanpin);
			map.put("title", "这是一个标题" + i);
			map.put("info", "这是一个详细信息" + i);
			list.add(map);
		}
		return list;
	}

	public List<Map<String, Object>> getData1() {
		TypedArray ar = getActivity().getApplicationContext().getResources().obtainTypedArray(
				R.array.actions_images);
		int len = ar.length();
		int[] resIds = new int[len];

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < len; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("image", ar.getResourceId(i, 0));
			list.add(map);
		}
		ar.recycle();
		return list;
	}
}