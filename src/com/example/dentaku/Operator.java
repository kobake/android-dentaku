package com.example.dentaku;

import java.math.BigDecimal;

//演算子
public class Operator{
	private String mValue = "";
	public void set(String text){
		mValue = text;
	}

	// -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- //
	// 初期化
	// -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- //
	public Operator(){
	}
	public Operator(String t){
		mValue = t;
	}
	
	// -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- //
	// 状態
	// -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- //
	public boolean isEmpty(){
		return mValue.isEmpty();
	}
	
	// -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- //
	// 計算
	// -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- //
	public Number calc(Number lhs, Number rhs) throws Exception{
		// 右辺未入力の場合、右辺値として左辺を代用する
		if(rhs.isEmpty())rhs = lhs;
		
		// 桁数
		if(lhs.getAppearance().length() > 20)throw new Exception("数値計算は20桁同士が限界です");
		if(rhs.getAppearance().length() > 20)throw new Exception("数値計算は20桁同士が限界です");
		
		// 数値に変換
		BigDecimal left = lhs.toBigDecimal();
		BigDecimal right = rhs.toBigDecimal();
		
		// 計算
		switch(mValue){
		case "+": return new Number(left.add     (right).toString());
		case "-": return new Number(left.subtract(right).toString());
		case "*": return new Number(left.multiply(right).toString());
		case "/":
			if(right.equals(BigDecimal.ZERO))throw new Exception("0で割ることはできません");
			return new Number(left.divide  (right).toString());
		default:
			throw new Exception("不明な演算子");
		}
	}
	
	// -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- //
	// 表示用情報
	// -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- //
	// デバッグ用表示
	public String getDebug(){
		if(mValue.equals(""))return "□";
		return mValue;
	}
}
