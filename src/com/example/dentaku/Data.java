package com.example.dentaku;

// データ統括(Singleton)
class Data{
	// Singleton
	private static Data data = new Data();
	public static Data getInstance(){
		return data;
	}
	
	// 左辺・演算子・右辺
	private Number[] mNumbers = new Number[2];
	private Operator mOp    = new Operator();
	
	// 現在の入力場所
	private int      mCur   = 0;
	
	// -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- //
	// 初期化
	// -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- //
	public Data(){
		clear();
	}
	
	// -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- //
	// 状態
	// -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- //
	// 現在の辺
	public int getCurrentIndex(){
		return mCur;
	}
	
	// -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- //
	// 操作
	// -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- //
	// 現在の辺を操作
	public void currentAdd(String text) throws Exception{ mNumbers[mCur].add(text); }
	public void currentClear()                          { mNumbers[mCur].clear(""); }
	public void currentBackSpace()                      { mNumbers[mCur].backSpace(); }
	
	// 演算子設定
	public void putOperator(String text) throws Exception{
		// 右辺があれば計算も行う
		if(mCur == 1 && !mNumbers[1].isEmpty()){
			calc();
		}
		// 演算子設定
		mOp.set(text);
		mCur = 1;
		mNumbers[1].clear(""); // 完全クリア
	}
	
	// クリア（ぜんぶ）
	public void clear(){
		mNumbers[0] = new Number();
		mNumbers[1] = new Number();
		mOp         = new Operator();
		mCur        = 0;
	}
	
	// -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- //
	// 計算
	// -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- //
	public void calc() throws Exception{
		mNumbers[0] = mOp.calc(mNumbers[0], mNumbers[1]);
		mCur = 0;
		mNumbers[0].nextReset(); // 次の数字入力で上書き
		// この次に「=」を打った場合、mOp,mRightが再利用される。
		// この次に「数字」「=」を打った場合、mLeftに数字が入り、mOp,mRightは再利用される。
		// この次に「演算子」「数字」「=」を打った場合、mOpは上書かれ、mRightも上書かれ、計算される。
	}
	
	// -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- //
	// 表示用情報
	// -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- //
	// デバッグ用表示
	public String getLeftDebug() { return mNumbers[0].getDebug(); }
	public String getOpDebug()   { return mOp.getDebug();         }
	public String getRightDebug(){ return mNumbers[1].getDebug(); }
	
	// 数値パネルへの表示
	public String getAppearance(){
		if(mNumbers[mCur].isEmpty())return mNumbers[0].getAppearance();
		return mNumbers[mCur].getAppearance();
	}
}
