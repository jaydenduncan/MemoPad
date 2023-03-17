package com.example.memopad;

public class MemoController extends AbstractController
{

    /*
     * These static property names are used as the identifiers for the elements
     * of the Models and Views which may need to be updated.  These updates can
     * be a result of changes to the Model which must be reflected in the View,
     * or a result of changes to the View (in response to user input) which must
     * be reflected in the Model.
     */

    public static final String MEMOTOADD_PROPERTY = "MemoToAdd";
    public static final String MEMOTODELETE_PROPERTY = "MemoToDelete";
    public static final String MEMOLIST_PROPERTY = "MemoList";


    public void changeMemoToAdd(String newMemo) {
        setModelProperty(MEMOTOADD_PROPERTY, newMemo);
    }

    public void changeMemoToDelete(int memoId) {
        setModelProperty(MEMOTODELETE_PROPERTY, memoId);
    }

    public void changeMemoList(String newText){
        setModelProperty(MEMOLIST_PROPERTY, newText);
    }

}
