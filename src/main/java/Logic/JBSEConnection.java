package Logic;

import Settings.SettingState;
import Settings.SettingsPersist;
import com.intellij.openapi.components.PersistentStateComponent;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Objects;

public class JBSEConnection {

    PersistentStateComponent<SettingState> state = new SettingsPersist().getInstance();
    private final String jbseHome = Objects.requireNonNull(state.getState()).jbseHome;
    private final String z3Path = Objects.requireNonNull(state.getState()).z3Path;
    private final String jreSourcepath = Objects.requireNonNull(state.getState().jdkPath);
    private final String jarPath = Objects.requireNonNull(state.getState().jarJBSEpath);

    private final String testHome;
    private final String methodClass;
    private final String methodDescriptor;
    private final String methodName;
    private final String testName;

    public JBSEConnection(String testHome, String methodClass, String methodDescriptor, String methodName, String testName) {
        this.testHome = testHome;
        this.methodClass = methodClass;
        this.methodDescriptor = methodDescriptor;
        this.methodName = methodName;
        this.testName = testName;
    }

    public void start() {
        //    public String projectPath = "C:/Users/PC/Documents/1.Projects/TRPO/examplesJBSE/";
//    public String methodDesc  = "(II)V";
//    public String outputClass = "out/ArrayDemoTest1.java";
//    public String methodName  = "entryPoint";
//    public String testingClass = "smalldemos/array_3/ArrayDemo3";
        final String z3Param = " -z3_path " + z3Path;
        final String jreParam = " -jre " + jreSourcepath;
        final String jbseHomeParam = " -jbse_home " + jbseHome;
        final String testHomeParam = " -test_home " + "C:/Users/andre/IdeaProjects/jbse-examplesxuyna/";
        final String classParam = " -mc " + "smalldemos/array_3/ArrayDemo3";
        final String descriptParam = " -md " + "(II)V";
        final String methodNameParam = " -mn " + "entryPoint";
        final String nameOfTestParam = " -tn " + "out/ArrayDemoTest1.java";
        final Runtime re = Runtime.getRuntime();

        try {
            re.exec("java -jar " + jarPath + jbseHomeParam + z3Param + testHomeParam + classParam + descriptParam + methodNameParam + nameOfTestParam + jreParam);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void start2(){
        final String jbseHomeParam = " -jbse_home " + jbseHome;
        final String z3Param = " -z3_path " + z3Path;
        final String testHomeParam = " -test_home " + testHome;
        final String classParam = " -mc " + methodClass;
        final String descriptParam = " -md " + methodDescriptor;
        final String methodNameParam = " -mn " + methodName;
        final String nameOfTestParam = " -tn " + testName;
        final String jreParam = " -jre " + jreSourcepath;
        final String fullPath = "java -jar " + jarPath + jbseHomeParam + z3Param + testHomeParam + classParam + descriptParam + methodNameParam + nameOfTestParam + jreParam;
        final Runtime re = Runtime.getRuntime();

        try{
            re.exec(fullPath);
        } catch (IOException ioe){
            ioe.printStackTrace();
        }
    }
}
