package com.example.dentaku;

import java.math.BigDecimal;

// 辺（左辺・右辺）
public class Number{
	private String  mValue = "";
	private boolean mToBeReset = false;
	
	// -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- //
	// 初期化
	// -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- //
	public Number(){
		mValue = "";
	}
	public Number(String t){
		mValue = t;
	}
	void set(Number n){
		mValue = n.mValue;
		mToBeReset = false;
	}
	
	// -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- //
	// 状態
	// -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- //
	public boolean isEmpty(){
		return mValue.isEmpty();
	}
	
	// -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- //
	// 操作
	// -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- //
	public void add(String text) throws Exception{
		// 文字数が1より大きい場合は1文字ずつ処理
		if(text.length() > 1){
			for(int i = 0; i < text.length(); i++){
				add(text.substring(i, i + 1));
			}
			return;
		}
		// 処理
		if(mToBeReset)mValue = "";
		if(mValue.length() >= 20)throw new Exception("数値入力は20桁が限界です");
		if(mValue.equals("0"))mValue = ""; // 先頭0除外
		mValue += text;
		mToBeReset = false;
	}
	public void clear(){
		mValue = "";
		mToBeReset = false;
	}
	public void backSpace(){
		if(mToBeReset)mValue = "";
		if(mValue.equals(""))return;
		mValue = mValue.substring(0, mValue.length() - 1);
		if(mValue.equals(""))mValue = "0";
		mToBeReset = false;
	}
	
	// -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- //
	// 特殊操作
	// -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- //
	// フラグセット：次に数値（またはBS）が入力された場合、この辺の中身はリセットされる。
	public void nextReset(){
		mToBeReset = true;
	}
	
	// -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- //
	// 表示用情報
	// -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- //
	// 数値パネルへの表示
	public String getAppearance(){
		if(mValue.equals(""))return "0";
		return mValue;
	}
	
	// デバッグ用表示
	public String getDebug(){
		if(mValue.equals(""))return "_";
		return mValue;
	}
	
	// -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- //
	// 計算用情報
	// -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- //
	// 計算用
	public BigDecimal toBigDecimal(){
		if(mValue.equals(""))return new BigDecimal("0");
		return new BigDecimal(mValue);
	}
}
