package Logic;

import javassist.ClassPool;
import javassist.CtMethod;
import javassist.NotFoundException;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*Работаем с байткодом для получения дескрипторов и имён методов*/
public class DescExtractor {

    ClassPool classPool;

    // Если файл находится в проекте где этот код
    public DescExtractor() {
        this.classPool = ClassPool.getDefault();
    }

    // Если файл класса находится по конкретному пути
    // C:\Users\andre\IdeaProjects\123\out\production\123\
    public DescExtractor(@NotNull final String absolutePath) throws NotFoundException {
        this.classPool = new ClassPool();
        classPool.insertClassPath(absolutePath);
    }

    // Если мы знаем только путь до нашего проекта, при том он на Maven/Gradle итд с разными названиями папок
    public DescExtractor(@NotNull final String projectPath,
                         @NotNull final String packagePath,
                         @NotNull final String projectName,
                         @NotNull final TypeBuild type) throws NotFoundException {
            final StringBuilder outDir = new StringBuilder();
            switch (type) {
                case CLA:
                    final String clazz = projectPath.substring(projectPath.lastIndexOf("/"));
                    outDir.append("out/production/").append(projectName).append(packagePath);
                    break;
                case MAVEN:
                    outDir.append("target/classes/").append(packagePath);
                    break;
                case GRADLE:
                    outDir.append("build/classes/").append(packagePath);
                    break;
                case ECLIPSE:
                    outDir.append("bin/").append(packagePath);
                    break;
            }

            this.classPool = new ClassPool();
            classPool.insertClassPath(projectPath + outDir.toString());
    }

    /**
     * @param className - имя класса который будем тестировать
     * @return ключь - название метода, значение - дескриптор
     */
    public Map<String, String> getAllDescriptorAndMethodName(@NotNull final String className) throws NotFoundException {
        final Map<String, String> map = new HashMap<>();
        final CtMethod[] ctMethods = this.classPool.get(className).getDeclaredMethods();
        for (CtMethod method: ctMethods) {
            map.put(method.getName(), method.getSignature());
        }
        return map;
    }

    public String getDescriptorOfOneMethod(@NotNull final String nameOfClass,
                                           @NotNull final String nameOfMethod) throws NotFoundException {
        return classPool.getMethod(nameOfClass, nameOfMethod).getSignature();
    }

    /**
     * list.get(0) - именна методов через запятую
     * list.get(1) - дескрипторы через запятую
     */
    public List<String> getAllSplittedNameAndDec(@NotNull final String className) throws NotFoundException {
        final StringBuilder names = new StringBuilder();
        final StringBuilder descriptors = new StringBuilder();
        final CtMethod[] ctMethods = classPool.get(className).getDeclaredMethods();
        for (CtMethod method: ctMethods) {
            names.append(method.getName());
            names.append(",");
            descriptors.append(method.getSignature());
            descriptors.append(",");
        }
        final List<String> list = new ArrayList<>();
        list.add(names.toString());
        list.add(descriptors.toString());
        return list;
    }
}
