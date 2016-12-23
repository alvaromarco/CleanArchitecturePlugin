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

    private ${CLOUD_ENTITY_DATA_SOURCE} mCloudAnnouncementsDataSource;
    private ${DISK_ENTITY_DATA_SOURCE} mDiskAnnouncementsDataSource;

    @Inject public ${NAME}() {
   
    }
    
    public ${CLOUD_ENTITY_DATA_SOURCE} getCloudDataSource() { 
    	if (mCloudAnnouncementsDataSource != null) {
            return mCloudAnnouncementsDataSource;
        } else {
            mCloudAnnouncementsDataSource = new ${CLOUD_ENTITY_DATA_SOURCE}(mCache);
            return mCloudAnnouncementsDataSource;
        }    	
    }
    
    public ${DISK_ENTITY_DATA_SOURCE} getDiskDataSource() { 
    	if (mDiskAnnouncementsDataSource != null) {
            return mDiskAnnouncementsDataSource;
        } else {
            mDiskAnnouncementsDataSource = new ${DISK_ENTITY_DATA_SOURCE}(mCache);
            return mDiskAnnouncementsDataSource;
        }   
    }

}