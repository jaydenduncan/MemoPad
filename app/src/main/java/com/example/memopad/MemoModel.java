package com.example.memopad;

import android.util.Log;

public class MemoModel extends AbstractModel {

    public static final String TAG = "MemoModel";
    private static final String EMPTY_TEXT = "Nothing to show here.";


    private DatabaseHandler db;
    private String memoList;
    private String memoToAdd;
    private String memoToDelete;

    /*
     * Initialize the model elements to known default values.  We use the setter
     * methods instead of changing the values directly so that these changes are
     * properly announced to the Controller, and so that the Views can be updated
     * accordingly.
     */

    public void init(MainActivity mainActivity) {

        db = new DatabaseHandler(mainActivity, null, null, 1);

        memoList = "";
        memoToAdd = "";
        memoToDelete = "";

        displayInitialList();

    }



    public void setMemoToAdd(String newMemo) {

        this.memoToAdd = newMemo;

        db.insertMemo(newMemo);

        Log.i(TAG, "Memo Added: " + newMemo);

        String updatedList = db.getAllMemos();

        setMemoList(updatedList);

    }

    public void setMemoToDelete(String memoId){

        this.memoToDelete = memoId;

        db.deleteMemo(memoId);

        Log.i(TAG, "ID of Deleted Memo: " + memoId);

        String updatedList = db.getAllMemos();

        setMemoList(updatedList);

    }

    public void displayInitialList(){

        String originalList = db.getAllMemos();

        if(originalList.equals("")){
            setMemoList(EMPTY_TEXT);
        }
        else{
            setMemoList(originalList);
        }

    }

    public void setMemoList(String newText){

        String oldText = this.memoList;
        this.memoList = newText;

        Log.i(TAG, "MemoList change from \n" + oldText + " to \n" + newText);

        firePropertyChange(MemoController.MEMOLIST_PROPERTY, oldText, newText);

    }

}
