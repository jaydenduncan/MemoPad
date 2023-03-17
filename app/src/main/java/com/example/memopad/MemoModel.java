package com.example.memopad;

import android.util.Log;

public class MemoModel extends AbstractModel {

    public static final String TAG = "MemoModel";
    private static final String EMPTY_TEXT = "Nothing to show here.";


    private DatabaseHandler db;
    private String memoList;
    private String memoToAdd;
    private int memoToDelete;

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
        memoToDelete = 0;

        displayInitialList();

    }



    public void setMemoToAdd(String newMemo) {

        this.memoToAdd = newMemo;

        db.insertMemo(newMemo);

        Log.i(TAG, "Memo Added: " + newMemo);

        String updatedList = db.getAllMemos();

        setMemoList(updatedList);

    }

    public void setMemoToDelete(int memoId) {

        this.memoToDelete = memoId;


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

        Log.i(TAG, "MemoList change from " + oldText + " to " + newText);

        firePropertyChange(MemoController.MEMOLIST_PROPERTY, oldText, newText);

    }

}
