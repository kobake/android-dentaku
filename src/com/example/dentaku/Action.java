package com.example.dentaku;

//-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- //
// 各ボタン処理規定
//-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- //
// 処理全般
interface ButtonAction{
	public void action(String text) throws Exception;
}

// -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- //
// 各ボタン処理
// -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- //
// 数字
class NumberButtonAction implements ButtonAction{
	public void action(String text) throws Exception{
		Data.getInstance().currentAdd(text);
	}
}

// 演算子
class OpButtonAction implements ButtonAction{
	public void action(String text) throws Exception{
		Data.getInstance().putOperator(text);
	}
}

// 計算
class CalcButtonAction implements ButtonAction{
	public void action(String text) throws Exception{
		Data.getInstance().calc();
	}
}

// クリア系
class ClearButtonAction implements ButtonAction{
	public void action(String text) throws Exception{
		if(text.equals("AC")){
			Data.getInstance().clear();
		}
		else if(text.equals("C")){
			Data.getInstance().currentClear();
		}
		else if(text.equals("BS")){
			Data.getInstance().currentBackSpace();
		}
	}
}

// メモリ系
class MemoryButtonAction implements ButtonAction{
	public void action(String text) throws Exception{
		if(text.equals("MR")){
			Data.getInstance().MemRead();
		}
		else if(text.equals("MC")){
			Data.getInstance().MemClear();
		}
		else if(text.equals("M+")){
			Data.getInstance().MemPlus();
		}
		else if(text.equals("M-")){
			Data.getInstance().MemMinus();
		}
	}
}

