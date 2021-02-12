package com.orane.icliniqlite.Model;


import com.orane.icliniqlite.R;

public enum ModelObject {

    RED(R.string.app_name, R.layout.home_fragment),
    BLUE(R.string.app_name, R.layout.home_fragment),
    GREEN(R.string.app_name, R.layout.common_activity);

    private int mTitleResId;
    private int mLayoutResId;

    ModelObject(int titleResId, int layoutResId) {
        mTitleResId = titleResId;
        mLayoutResId = layoutResId;
    }

    public int getTitleResId() {
        return mTitleResId;
    }

    public int getLayoutResId() {
        return mLayoutResId;
    }

}