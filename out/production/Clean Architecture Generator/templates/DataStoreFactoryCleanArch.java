#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end

import ${PACKAGE_ENTITY_SERVICE}.${ENTITY_SERVICE};
import ${PACKAGE_ENTITY_CACHE}.${ENTITY_CACHE};
import ${PACKAGE_CLOUD_ENTITY_DATA_SOURCE}.${CLOUD_ENTITY_DATA_SOURCE};
import ${PACKAGE_DISK_ENTITY_DATA_SOURCE}.${DISK_ENTITY_DATA_SOURCE};

import javax.inject.Inject;

#parse("File Header.java")
public class ${NAME} {

    @Inject ${ENTITY_SERVICE} mService;
    @Inject ${ENTITY_CACHE} mCache;
    
    @Inject public ${NAME}() {
   
    }
    
    public ${CLOUD_ENTITY_DATA_SOURCE} getCloudDataSource() { return new ${CLOUD_ENTITY_DATA_SOURCE}(mService); }
    
    public ${DISK_ENTITY_DATA_SOURCE} getDiskDataSource() { return new ${DISK_ENTITY_DATA_SOURCE}(mCache); }

}