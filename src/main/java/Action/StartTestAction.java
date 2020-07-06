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
<<<<<<< HEAD
import jdk.vm.ci.meta.Local;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.*;
=======
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
>>>>>>> tmp

import static com.intellij.openapi.actionSystem.CommonDataKeys.VIRTUAL_FILE;
import static java.lang.Thread.sleep;

public class StartTestAction extends AnAction {
    private JBSEConnection jbseConnection;
    // Для JBSE
<<<<<<< HEAD
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

=======
    private String projectPath;
    private String outputClass;
    private String testingClass;
    private List<String> namesAndDescriptors;

    private static final String GEN_FOLDER_NAME = "GeneratedTest";
    private VirtualFile fileIndokedPopUpMenu;
    private VirtualFile pathToProject;

    @Override
    public void actionPerformed(@NotNull final AnActionEvent e) {
        // Получаем файл по которому мы нажали ПКМ
        fileIndokedPopUpMenu = e.getData(VIRTUAL_FILE);
        // получаем путь до проекта
        projectPath = Objects.requireNonNull(e.getProject()).getBasePath() + "/";

        // виртуальный файл корня проекта, чтобы можно было рекурсивно обновлять весь проект
        pathToProject = LocalFileSystem.getInstance().findFileByPath(projectPath);
        VirtualFile generatedDir = LocalFileSystem.getInstance().findFileByPath(projectPath + GEN_FOLDER_NAME + "/");
        pathToProject.refresh(true,true);

        // если такой папки нет создаем
        if(generatedDir == null){
            try {
                generatedDir = LocalFileSystem.getInstance().createChildDirectory(this, pathToProject, GEN_FOLDER_NAME);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        pathToProject.refresh(true,true);

        final String javaFileName = fileIndokedPopUpMenu.getName().replace(".java", "");
        testingClass = Objects.requireNonNull(fileIndokedPopUpMenu.getCanonicalPath())
                .replace(projectPath + "src/main/java/", "");
        outputClass = "GeneratedTest/" + javaFileName + "Test.java";

        final String[] temp = projectPath.split("/");
        final String projectName = temp[temp.length - 1] + "/";

        // Путь пакетов до целевого файла
        final String tmp = testingClass.replace(projectPath + "src/", "");
        final String packagePath = tmp.substring(0, tmp.lastIndexOf("/") + 1);

        // Получаем дескрипторы и имена методов
        try {
            final DescExtractor descExtractor = new DescExtractor(projectPath, packagePath, projectName, typeOfProject(packagePath, projectPath));
            namesAndDescriptors = descExtractor.getAllSplittedNameAndDec(javaFileName);
        } catch (NotFoundException notFoundException) {
            Messages.showMessageDialog("The corresponding .class file does not exist. Compile the project and try again.", "Error",
                    Messages.getInformationIcon());
        }

        runJBSE(e);
        updateWhileNonVisible(e);

        Messages.showMessageDialog(e.getProject(), "Test generation is completed", "Generation of test",
                Messages.getInformationIcon());
    }

    private void runJBSE(@NotNull final AnActionEvent e) {
        jbseConnection = new JBSEConnection(projectPath, testingClass, namesAndDescriptors.get(1), namesAndDescriptors.get(0), outputClass);
>>>>>>> tmp
        CommandProcessor.getInstance().executeCommand(e.getProject(), () -> {
            System.out.println("Starting generation");
            jbseConnection = new JBSEConnection();
            jbseConnection.start();
//            jbseConnection.start2();
            System.out.println("Symbolic execution is completed");
        },  VcsBundle.message("command.name.open.error.message.view"), null);
//        updateWhileNonVisible(e);

<<<<<<< HEAD
//        String exit = "testHome " + projectPath + "\n methodClass" + testingClass
//                + "\n methodDescriptor " + namesAndDescriptors.get(1) +
//                "\n methodName " + namesAndDescriptors.get(0)
//                + "\n testName " + outputClass;
//        Messages.showMessageDialog(exit, "Generation of test",
//                Messages.getInformationIcon());
=======
    private void updateWhileNonVisible(@NotNull final AnActionEvent e) {
        VirtualFile virtualFile;
        do {
            try {
                sleep(500);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
>>>>>>> tmp

        Messages.showMessageDialog(e.getProject(), "Test generation is completed", "Generation of test",
                Messages.getInformationIcon());
    }

<<<<<<< HEAD

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
=======
    private TypeBuild typeOfProject(@NotNull final String projectName,
                                    @NotNull final String packagePath) {
        final VirtualFile maven = LocalFileSystem.getInstance().findFileByPath(projectPath + "target/classes/" + packagePath);
        final VirtualFile gradle = LocalFileSystem.getInstance().findFileByPath(projectPath + "build/classes/" + packagePath);
        final VirtualFile cla = LocalFileSystem.getInstance().findFileByPath(projectPath + "out/production/" + projectName + packagePath);

        if (maven != null) {
            return TypeBuild.MAVEN;
        } else if (gradle != null) {
            return TypeBuild.GRADLE;
        } else if (cla != null){
            return TypeBuild.CLA;
        } else {
            return TypeBuild.ECLIPSE;
        }
    }
>>>>>>> tmp
}
