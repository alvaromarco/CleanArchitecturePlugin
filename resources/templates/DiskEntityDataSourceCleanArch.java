#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end

import ${PACKAGEE_BASE_SERVICE}.${BASE_SERVICE};
import ${PACKAGE_CACHE}.${CACHE};

import javax.inject.Inject;

import rx.Observable;

#parse("File Header.java")
public class ${NAME} extends ${BASE_SERVICE} {

    ${CACHE} mCache;
    
    @Inject public ${NAME}(${CACHE} cache) { this.mCache = cache; }
    
    // TODO: Add methods to connect with mCache
}