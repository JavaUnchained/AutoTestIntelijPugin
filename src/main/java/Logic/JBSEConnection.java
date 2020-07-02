package Logic;

import Settings.SettingState;
import Settings.SettingsPersist;
import com.intellij.openapi.components.PersistentStateComponent;

import java.io.IOException;
import java.util.Objects;

public class JBSEConnection {

    PersistentStateComponent<SettingState> state = new SettingsPersist().getInstance();
    private String jbseHome = Objects.requireNonNull(state.getState()).jbseHome;
    private String z3Path = Objects.requireNonNull(state.getState()).z3Path;
    private String jreSourcepath = Objects.requireNonNull(state.getState().jdkPath);
    private String jarPath = Objects.requireNonNull(state.getState().jarJBSEpath);

    private String testHome;
    private String methodClass;
    private String methodDescriptor;
    private String methodName;
    private String testName;

    public JBSEConnection(String testHome, String methodClass, String methodDescriptor, String methodName, String testName) {
        this.testHome = testHome;
        this.methodClass = methodClass;
        this.methodDescriptor = methodDescriptor;
        this.methodName = methodName;
        this.testName = testName;
    }

    public JBSEConnection() {
    }

    public void start() {
        //    public String projectPath = "C:/Users/PC/Documents/1.Projects/TRPO/examplesJBSE/";
//    public String methodDesc  = "(II)V";
//    public String outputClass = "out/ArrayDemoTest1.java";
//    public String methodName  = "entryPoint";
//    public String testingClass = "smalldemos/array_3/ArrayDemo3";
        String jarParam = jarPath;
        String z3Param = " -z3_path " + z3Path;
        String jreParam = " -jre " + jreSourcepath;
        String jbseHomeParam = " -jbse_home " + jbseHome;
        String testHomeParam = " -test_home " + "C:/Users/andre/IdeaProjects/jbse-examplesxuyna/";
        String classParam = " -mc " + "smalldemos/array_3/ArrayDemo3";
        String descriptParam = " -md " + "(II)V";
        String methodNameParam = " -mn " + "entryPoint";
        String nameOfTestParam = " -tn " + "out/ArrayDemoTest1.java";
        Runtime re = Runtime.getRuntime();

        try {
            re.exec("java -jar " + jarParam + jbseHomeParam + z3Param + testHomeParam + classParam + descriptParam + methodNameParam + nameOfTestParam + jreParam);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void start2(){
        String jarParam = jarPath;
        String jbseHomeParam = " -jbse_home " + jbseHome;
        String z3Param = " -z3_path " + z3Path;
        String testHomeParam = " -test_home " + testHome;
        String classParam = " -mc " + methodClass;
        String descriptParam = " -md " + methodDescriptor;
        String methodNameParam = " -mn " + methodName;
        String nameOfTestParam = " -tn " + testName;
        String jreParam = " -jre " + jreSourcepath;
        Runtime re = Runtime.getRuntime();

        try{
            re.exec("java -jar " + jarParam + jbseHomeParam + z3Param + testHomeParam+ classParam + descriptParam + methodNameParam+ nameOfTestParam + jreParam);
        } catch (IOException ioe){
            ioe.printStackTrace();
        }
    }
}
