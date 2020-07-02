package Logic;

import javassist.ClassPool;
import javassist.CtMethod;
import javassist.NotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
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
    public DescExtractor(String absolutePath) throws NotFoundException {
        this.classPool = new ClassPool();
        classPool.insertClassPath(absolutePath);
    }

    // Если мы знаем только путь до нашего проекта, при том он на Maven/Gradle итд с разными названиями папок
    public DescExtractor(String projectPath, TypeBuild type) throws NotFoundException {
            String outDir = "";
            switch (type) {
//                case CLA: outDir = "out/production/untitled1/";
                case CLA: outDir = "out/";
                    break;
                case MAVEN:
                    outDir = "target/classes/";
                    break;
                case GRADLE:
                    outDir = "build/classes/";
                    break;
            }

            this.classPool = new ClassPool();
            classPool.insertClassPath(projectPath + outDir);
    }

    /**
     * @param className - имя класса который будем тестировать
     * @return ключь - название метода, значение - дескриптор
     */
    public Map<String, String> getAllDescriptorAndMethodName(String className) throws NotFoundException {
        Map<String, String> map = new HashMap<>();
        CtMethod[] ctMethods = this.classPool.get(className).getDeclaredMethods();
        for (CtMethod method: ctMethods) {
            map.put(method.getName(), method.getSignature());
        }
        return map;
    }

    public String getDescriptorOfOneMethod(String nameOfClass, String nameOfMethod) throws NotFoundException {
        return classPool.getMethod(nameOfClass, nameOfMethod).getSignature();
    }

    /**
     * list.get(0) - именна методов через запятую
     * list.get(1) - дескрипторы через запятую
     */
    public ArrayList<String> getAllSplittedNameAndDec(String className) throws NotFoundException {
        StringBuilder names = new StringBuilder();
        StringBuilder descriptors = new StringBuilder();
        CtMethod[] ctMethods = classPool.get(className).getDeclaredMethods();
        for (CtMethod method: ctMethods) {
            names.append(method.getName());
            names.append(",");
            descriptors.append(method.getSignature());
            descriptors.append(",");
        }
        ArrayList<String> list = new ArrayList();
        list.add(names.toString());
        list.add(descriptors.toString());
        return list;
    }
}
