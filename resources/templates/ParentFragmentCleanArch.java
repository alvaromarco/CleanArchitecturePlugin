package com.digio.hablemos.main.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.view.inputmethod.InputMethodManager;

import ${PACKAGE_PRESENTER}.${PRESENTER};


#parse("File Header.java")
public abstract class ${NAME} extends Fragment {

    public void initPresenter() {
        initInjector();
        if(getPresenter() != null) {
            getPresenter().setView(this);
        }
    }

    public abstract void initInjector();

    @Override public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initPresenter();
    }

    @Override public void onResume() {
        super.onResume();

        ${PRESENTER} presenter = getPresenter();
        if (presenter != null) {
            presenter.resume();
        }
    }

    @Override public void onPause() {
        super.onPause();

        ${PRESENTER} presenter = getPresenter();
        if (presenter != null) {
            presenter.pause();
        }
    }

    @Override public void onDestroy() {
        super.onDestroy();

        ${PRESENTER} presenter = getPresenter();
        if (presenter != null) {
            presenter.destroy();
        }
    }

    public abstract ${PRESENTER} getPresenter();

    public void hideKeyboard(Context context, IBinder windowToken) {
        InputMethodManager mgr = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        mgr.hideSoftInputFromWindow(windowToken, 0);
    }
}
