#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end

#parse("File Header.java")

public interface ${NAME}{

    void create();

    void resume();

    void fromBackground();

    void pause();

    void destroy();


    // TODO: Add your methods

}