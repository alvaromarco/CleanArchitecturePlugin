#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end

import ${PACKAGE_SERVICE}.${SERVICE};

import javax.inject.Inject;

import rx.Observable;

#parse("File Header.java")
public class ${NAME}{
    ${SERVICE} mService;
    
    @Inject public ${NAME}(${SERVICE} service) { this.mService = service; }
    
    // TODO: Add actions to conect with mService
}