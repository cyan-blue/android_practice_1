package com.xiaolumeimei.yann;

import java.util.HashMap;
import java.util.Map;

import com.xiaolumeimei.common.view.ViewPagerIndicatorView;
import com.xiaolumeimei.ui.HotsaleFragment;
import com.xiaolumeimei.ui.MeFragment;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends FragmentActivity implements OnClickListener {
	private HotsaleFragment hf;
	private MeFragment mf;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		hf = new HotsaleFragment();
		mf = new MeFragment();
		findViewById(R.id.todayHotsale).setOnClickListener(this);
		findViewById(R.id.search).setOnClickListener(this);
		findViewById(R.id.findMei).setOnClickListener(this);
		findViewById(R.id.shopCart).setOnClickListener(this);
		findViewById(R.id.xiaolumeimeiMe).setOnClickListener(this);
		findViewById(R.id.todayHotsale).performClick();

	}

	// 点击返回按钮
	@Override
	public void onBackPressed() {
		super.onBackPressed();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.todayHotsale:
			addFragmentToStack(hf);
			break;
		case R.id.search:
			addFragmentToStack(FragmentParent.newInstance(0));
			break;
		case R.id.findMei:
			addFragmentToStack(FragmentParent.newInstance(1));
			break;
		case R.id.shopCart:
			addFragmentToStack(FragmentParent.newInstance(2));
			break;
		case R.id.xiaolumeimeiMe:
			addFragmentToStack(mf);
			break;
		}
	}

	private void addFragmentToStack(Fragment fragment) {
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		// ft.setCustomAnimations(android.R.anim.slide_in_left,
		// android.R.anim.slide_in_left);
		ft.replace(R.id.fragment_container, fragment);
		ft.commit();
	}

	/** 嵌套Fragment */
	public final static class FragmentParent extends Fragment {

		public static final FragmentParent newInstance(int position) {
			FragmentParent f = new FragmentParent();
			Bundle args = new Bundle(2);
			args.putInt("position", position);
			f.setArguments(args);
			return f;
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View convertView = inflater.inflate(R.layout.viewpager_fragments,
					container, false);
			ViewPager pager = (ViewPager) convertView.findViewById(R.id.pager);

			final int parent_position = getArguments().getInt("position");
			// 注意这里的代码
			pager.setAdapter(new FragmentStatePagerAdapter(
					getChildFragmentManager()) {

				@SuppressLint("ValidFragment")
				@Override
				public Fragment getItem(final int position) {
					return new Fragment() {
						@Override
						public View onCreateView(LayoutInflater inflater,
								ViewGroup container, Bundle savedInstanceState) {
							TextView convertView = new TextView(getActivity());
							convertView
									.setLayoutParams(new ViewGroup.LayoutParams(
											ViewGroup.LayoutParams.FILL_PARENT,
											ViewGroup.LayoutParams.FILL_PARENT));
							convertView.setGravity(Gravity.CENTER);
							convertView.setTextSize(30);
							convertView.setTextColor(Color.BLACK);
							convertView.setText("Page " + position);
							return convertView;
						}
					};
				}

				@Override
				public int getCount() {
					return 4;
				}

				@Override
				public CharSequence getPageTitle(int position) {
					return "Page " + parent_position + " - " + position;
				}

			});

			return convertView;
		}
	}
}
