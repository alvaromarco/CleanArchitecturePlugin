#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MenuItem;

import ${PACKAGE_PROJECT}.R;

import ${PACKAGE_PRESENTER}.${PRESENTER};


#parse("File Header.java")
public abstract class ${NAME} extends AppCompatActivity {

    public void initPresenter() {
        initInjector();
        if(getPresenter() != null) {
            getPresenter().setView(this);
        }
    }

    public abstract void initInjector();

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initPresenter();
    }

    @Override protected void onResume() {
        super.onResume();

        ${PRESENTER} presenter = getPresenter();
        if (presenter != null) {
            presenter.resume();
        }
    }

    @Override protected void onPause() {
        super.onPause();

        ${PRESENTER} presenter = getPresenter();
        if (presenter != null) {
            presenter.pause();
        }
    }

    @Override protected void onStart() {
        super.onStart();
        //EventBus.getDefault().register(this);
    }

    @Override protected void onStop() {
        super.onStop();
        //EventBus.getDefault().unregister(this);
    }

    @Override protected void onDestroy() {
        super.onDestroy();

        Presenter presenter = getPresenter();
        if (presenter != null) {
            presenter.destroy();
        }
    }

    public abstract ${PRESENTER} getPresenter();

    public void loadFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commitAllowingStateLoss();
    }

    public Fragment getCurrentFragment(){
        return getSupportFragmentManager().findFragmentById(R.id.container);
    }


    @Override public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
