#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end

#parse("File Header.java")

public interface ${NAME}<T>{

    void setView(T view);
    
    void create();

    void resume();

    void fromBackground();

    void pause();

    void destroy();


    // TODO: Add your methods

}