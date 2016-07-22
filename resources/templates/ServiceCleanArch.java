#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end

import ${PACKAGE_BASE_SERVICE}.${BASE_SERVICE};
import javax.inject.Inject;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

#parse("File Header.java")
public class ${NAME} extends ${BASE_SERVICE} {
    
    private Service mService;
    
    @Inject public ${NAME}() { this.mService = getAdapter().create(Service.class); }
    
    // TODO: Create methods to conect with API 
    
    private interface Service {
        // TODO: Complete with calls
    }
    
}