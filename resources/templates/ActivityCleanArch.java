#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end

import android.os.Bundle;
import android.support.v4.app.Fragment;
import ${PACKAGE_PROJECT}.R;

import javax.inject.Inject;

#if (${BASE_ACTIVITY} && ${BASE_ACTIVITY} != "") 
import ${PACKAGE_BASE_ACTIVITY}.${BASE_ACTIVITY}; 
#else 
import android.support.v7.app.AppCompatActivity;
#end

#if (${BASE_PRESENTER} && ${BASE_PRESENTER} != "") import ${PACKAGE_BASE_PRESENTER}.${BASE_PRESENTER}; #end
#if (${PRESENTER} && ${PRESENTER} != "") import ${PACKAGE_PRESENTER}.${PRESENTER};#end


#parse("File Header.java")
public class ${NAME} extends #if (${BASE_ACTIVITY} && ${BASE_ACTIVITY} != "") ${BASE_ACTIVITY} #else AppCompatActivity #end #if (${PRESENTER} && ${PRESENTER} != "") implements ${PRESENTER}.View #end {

    #if (${PRESENTER} && ${PRESENTER} != "") @Inject ${PRESENTER} mPresenter;#end

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_${LAYOUT_NAME});
       
        #if (${PRESENTER} && ${PRESENTER} != "")mPresenter.create();#end

    }
    
    @Override public void initInjector() {
        
    }
    
    #if (${BASE_PRESENTER} && ${BASE_PRESENTER} != "") 
    @Override public ${BASE_PRESENTER} getPresenter() {
        return null;
    }#end
    
}