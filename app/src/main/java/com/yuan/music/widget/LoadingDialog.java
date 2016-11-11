package com.yuan.music.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

import com.yuan.music.R;


public class LoadingDialog extends Dialog {
	Context context;
	TextView text;
	String hint = "";
	boolean isBackkeyWork = true;

	public LoadingDialog(Context context) {
		super(context);
		this.context = context;
	}

	public LoadingDialog(Context context, boolean isBackkeyWork) {
		super(context);
		this.context = context;
		this.isBackkeyWork = isBackkeyWork;
	}

	public LoadingDialog(Context context, String hint) {
		super(context);
		this.context = context;
		this.hint = hint;
	}

	public LoadingDialog(Context context, String hint, boolean isBackkeyWork) {
		super(context);
		this.context = context;
		this.hint = hint;
		this.isBackkeyWork = isBackkeyWork;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setBackgroundDrawableResource(android.R.color.transparent);
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.dialog_loading);
		WindowManager.LayoutParams p = getWindow().getAttributes();
		p.dimAmount = 0.5f;
		getWindow().setAttributes(p);

		imageView =  findViewById(R.id.img);
		text = (TextView) findViewById(R.id.loading_text);
		animation = AnimationUtils.loadAnimation(context, R.anim.circle_center);

		animation.setInterpolator(new LinearInterpolator());
		if (!"".equals(hint)) {
			text = (TextView) findViewById(R.id.loading_text);
			text.setVisibility(View.VISIBLE);
			text.setText(hint);
		} else {
			text.setVisibility(View.GONE);
		}
	}

	View imageView;
	Animation animation;

	@Override
	public void show() {
		super.show();
		imageView.startAnimation(animation);

	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		if (isBackkeyWork) {
			dismiss();
		}
	}
}
