package Action;

import Logic.DescExtractor;
import Logic.JBSEConnection;
import Logic.TypeBuild;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.command.CommandProcessor;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.fileTypes.FileTypeManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectLocator;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vcs.VcsBundle;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.search.FileTypeIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.util.PathsList;
import com.intellij.util.ResourceUtil;
import com.intellij.util.indexing.FileBasedIndex;
import javassist.NotFoundException;
import jdk.vm.ci.meta.Local;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.*;

import static com.intellij.openapi.actionSystem.CommonDataKeys.VIRTUAL_FILE;
import static java.lang.Thread.sleep;

public class StartTestAction extends AnAction {
    private JBSEConnection jbseConnection;
    // Для JBSE
//    private String projectPath;
//    private String outputClass;
//    private String testingClass;
//    private ArrayList<String> namesAndDescriptors;
//
//    private String javaFileName;
//
//    private static final String GEN_FOLDER_NAME = "GeneratedTest";
//    private VirtualFile fileIndokedPopUpMenu;
//    private VirtualFile pathToProject;

    @Override
    public void actionPerformed(AnActionEvent e) {
//        // Получаем файл по которому мы нажали ПКМ
//        fileIndokedPopUpMenu = e.getData(VIRTUAL_FILE);
//        // получаем путь до проекта
//        projectPath = Objects.requireNonNull(e.getProject()).getBasePath() + "/";
//
//        // виртуальный файл корня проекта, чтобы можно было рекурсивно обновлять весь проект
//        pathToProject = LocalFileSystem.getInstance().findFileByPath(projectPath);
//        VirtualFile generatedDir = LocalFileSystem.getInstance().findFileByPath(projectPath + GEN_FOLDER_NAME + "/");
//        pathToProject.refresh(true,true);
//
//        // если такой папки нет создаем
//        if(generatedDir == null){
//            try {
//                generatedDir = LocalFileSystem.getInstance().createChildDirectory(this, pathToProject, GEN_FOLDER_NAME);
//            } catch (IOException ioException) {
//                ioException.printStackTrace();
//            }
//        }
//        pathToProject.refresh(true,true);
//
//        javaFileName = fileIndokedPopUpMenu.getName().replace(".java", "");
//        testingClass = fileIndokedPopUpMenu.getCanonicalPath().replace(projectPath+
//                "src/main/java/", "");
//        testingClass = fileIndokedPopUpMenu.getCanonicalPath().replace(projectPath+"src/", "");
//        outputClass = "GeneratedTest/" + javaFileName + "Test.java";
//
//        // Получаем дескрипторы и имена методов
//        try {
//            DescExtractor descExtractor = new DescExtractor(projectPath, typeOfProject());
//            namesAndDescriptors = descExtractor.getAllSplittedNameAndDec(javaFileName);
//        } catch (NotFoundException notFoundException) {
//            Messages.showMessageDialog("The corresponding .class file does not exist. Compile the project and try again.", "Error",
//                    Messages.getInformationIcon());
//        }

        CommandProcessor.getInstance().executeCommand(e.getProject(), () -> {
            System.out.println("Starting generation");
            jbseConnection = new JBSEConnection();
            jbseConnection.start();
//            jbseConnection.start2();
            System.out.println("Symbolic execution is completed");
        },  VcsBundle.message("command.name.open.error.message.view"), null);
//        updateWhileNonVisible(e);

//        String exit = "testHome " + projectPath + "\n methodClass" + testingClass
//                + "\n methodDescriptor " + namesAndDescriptors.get(1) +
//                "\n methodName " + namesAndDescriptors.get(0)
//                + "\n testName " + outputClass;
//        Messages.showMessageDialog(exit, "Generation of test",
//                Messages.getInformationIcon());

        Messages.showMessageDialog(e.getProject(), "Test generation is completed", "Generation of test",
                Messages.getInformationIcon());
    }


//    private void updateWhileNonVisible(AnActionEvent e) {
//        VirtualFile virtualFile;
//        do {
//            try {
//                sleep(500);
//            } catch (InterruptedException ex) {
//                ex.printStackTrace();
//            }
//
//            virtualFile = LocalFileSystem.getInstance().findFileByPath(projectPath+outputClass);
//            pathToProject.refresh(true,true);
//        } while (virtualFile == null);
//    }

//    private TypeBuild typeOfProject() {
//        VirtualFile maven = LocalFileSystem.getInstance().findFileByPath(projectPath+"target/classes/");
//        VirtualFile gradle = LocalFileSystem.getInstance().findFileByPath(projectPath + "build/");
//        TypeBuild typeBuild;
//
//        if (maven != null) {
//            typeBuild = TypeBuild.MAVEN;
//        } else if (gradle != null) {
//            typeBuild = TypeBuild.GRADLE;
//        } else {
//            typeBuild = TypeBuild.CLA;
//        }
//        return typeBuild;
//    }
}
