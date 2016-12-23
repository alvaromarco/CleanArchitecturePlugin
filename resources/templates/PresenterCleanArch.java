#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end

import javax.inject.Inject;

import ${PACKAGE_PRESENTER_IMPL}.${PRESENTER_IMPL};
#parse("File Header.java")
public class ${NAME} extends ${PRESENTER_IMPL}<${NAME}.View>{

    // TODO: Add your useCase

    private View mView;
    
    @Inject public ${NAME}() {
    }
    
    @Override public void setView(${NAME}.View view) {
        this.mView = view;
    }


    public interface View {
        //TODO: Create methods to implements in Activity or Fragment
    }    
    
}
