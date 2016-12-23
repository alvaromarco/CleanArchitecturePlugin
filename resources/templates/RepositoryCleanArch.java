#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end

import javax.inject.Inject;

import rx.Observable;

#parse("File Header.java")
public class ${NAME} {

    @Inject ${DATA_STORE_FACTORY} mFactory;
    
    @Inject public ${NAME}() {
    }
    
    // TODO: Add methods to connect with mFactory
}