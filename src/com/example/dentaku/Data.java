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
	
	// メモリ
	private Number   mMem   = new Number("0");
	
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
	
	// メモリ機能
	public void MemRead() { mNumbers[mCur].set(mMem); mNumbers[mCur].nextReset(); }
	public void MemClear(){ mMem = new Number("0"); }
	public void MemPlus() throws Exception { mMem = new Operator("+").calc(mMem, getAppearanceNumber()); }
	public void MemMinus() throws Exception{ mMem = new Operator("-").calc(mMem, getAppearanceNumber()); }
	
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
	public String getOpDebug()   { return mOp        .getDebug(); }
	public String getRightDebug(){ return mNumbers[1].getDebug(); }
	public String getMemDebug()  { return mMem       .getDebug(); }
	
	// 数値パネルへの表示
	public String getAppearance(){
		return getAppearanceNumber().getAppearance();
	}
	public Number getAppearanceNumber(){
		if(mNumbers[mCur].isEmpty())return mNumbers[0];
		return mNumbers[mCur];
	}
}
