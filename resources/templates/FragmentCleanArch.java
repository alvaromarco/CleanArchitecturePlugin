#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ${PACKAGE_PROJECT}.R;

import ${PACKAGE_BASE_FRAGMENT}.${BASE_FRAGMENT};
import ${PACKAGE_BASE_PRESENTER}.${BASE_PRESENTER};

#if (${PRESENTER} && ${PRESENTER} != "") import ${PACKAGE_PRESENTER}.${PRESENTER};#end

#parse("File Header.java")
public class ${NAME} extends ${BASE_FRAGMENT}  #if (${PRESENTER} && ${PRESENTER} != "") implements ${PRESENTER}.View #end {

    #if (${PRESENTER} && ${PRESENTER} != "") @Inject ${PRESENTER} mPresenter;#end

    @Nullable @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_${LAYOUT_NAME}, container, false);
        return view;
    }
    
    @Override public void initInjector() {

    }
    
    #if (${BASE_PRESENTER} && ${BASE_PRESENTER} != "") 
    @Override public ${BASE_PRESENTER} getPresenter() {
        #if (${BASE_PRESENTER} && ${BASE_PRESENTER} != "") 
        return mPresenter;
        #else 
        return null;
        #end
    }#end
    
}