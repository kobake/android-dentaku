package com.example.dentaku;

import java.util.HashMap;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	// 数字表示部分
	TextView mTextView;
	
	// 上部のデバッグ用表示
	TextView[] mTextViewNumbers = new TextView[2];
	TextView mTextViewOp;
	
	// 処理リスト
	HashMap<String, ButtonAction> mActions = new HashMap<String, ButtonAction>();

	// -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- //
	// 初期化
	// -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- //
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// TextView
		mTextView = (TextView)findViewById(R.id.textView1);
		mTextViewNumbers[0] = (TextView)findViewById(R.id.textViewLeft);
		mTextViewNumbers[1] = (TextView)findViewById(R.id.textViewRight);
		mTextViewOp = (TextView)findViewById(R.id.textViewOp);
		
		// -- -- 処理 -- -- //
		// 数字
		mActions.put("0", new NumberButtonAction());
		mActions.put("00", new NumberButtonAction());
		mActions.put("1", new NumberButtonAction());
		mActions.put("2", new NumberButtonAction());
		mActions.put("3", new NumberButtonAction());
		mActions.put("4", new NumberButtonAction());
		mActions.put("5", new NumberButtonAction());
		mActions.put("6", new NumberButtonAction());
		mActions.put("7", new NumberButtonAction());
		mActions.put("8", new NumberButtonAction());
		mActions.put("9", new NumberButtonAction());
		// 演算子
		mActions.put("+", new OpButtonAction());
		mActions.put("-", new OpButtonAction());
		mActions.put("*", new OpButtonAction());
		mActions.put("/", new OpButtonAction());
		// 実行
		mActions.put("=", new CalcButtonAction());
		// クリア系
		mActions.put("AC", new ClearButtonAction());
		mActions.put("C",  new ClearButtonAction());
		mActions.put("BS", new ClearButtonAction());
		// メモリ系
		mActions.put("MR", new MemoryButtonAction());
		mActions.put("MC", new MemoryButtonAction());
		mActions.put("M+", new MemoryButtonAction());
		mActions.put("M-", new MemoryButtonAction());
		
		// -- -- 表示 -- -- //
		updateViews();
	}
	
	// -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- //
	// 主処理
	// -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- //
	// 表示更新
	void updateViews(){
		// メインの表示パネル
		mTextView.setText(Data.getInstance().getAppearance());
		// 上部のデバッグ用パネル
		mTextViewNumbers[0].setText(Data.getInstance().getLeftDebug());
		mTextViewOp.setText(Data.getInstance().getOpDebug());
		mTextViewNumbers[1].setText(Data.getInstance().getRightDebug());
		// 入力中の辺を強調表示
		int current = Data.getInstance().getCurrentIndex();
		mTextViewNumbers[current].setTextColor(Color.rgb(255, 0, 0)); // 強調色
		mTextViewNumbers[1 - current].setTextColor(Color.rgb(0, 0, 0)); // 通常色
	}

	// ボタン押下時
	public void buttonMethod(View _button){
		Button button = (Button)_button;
		String text = button.getText().toString();
		try{
			// データの更新
			mActions.get(text).action(text);
			// 表示の更新
			updateViews();
		}
		catch(Exception ex){
			Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
		}
	}

	// -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- //
	// 以下メニュー等。特にいじらない。
	// -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- //
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
