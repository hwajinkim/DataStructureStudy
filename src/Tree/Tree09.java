package Tree;

import java.util.HashMap;
import java.util.LinkedList;

//Graph에 명시된 관계에 따라 데이터 뽑기
class Project{
    private String name; //이름
    private LinkedList<Project> dependencies;//선행되어야 할 프로젝트 리스트 선언
    private boolean marked;//순서 정할때 결과에 담았는지 확인
    public Project(String name){
        this.name = name;
        this.marked = false;
        this.dependencies = new LinkedList<Project>();
    }

    public void addDependency(Project project){ //의존 관계 추가
        //처리되어야 할 프로젝트 받아서 linkedlist에 추가
        if(!dependencies.contains(project)){
            dependencies.add(project);
        }
    }

    public LinkedList<Project> getDependencies(){
        return this.dependencies;
    }
    public String getName(){
        return this.name;
    }
    public void setMarked(boolean marked){
        this.marked = marked;
    }
    public boolean getMarked(){
        return this.marked;
    }
}
class ProjectManager {
    private HashMap<String, Project> projects; //프로젝트 이름으로 검색하기 용이
    public ProjectManager(String[] names, String[][] dependencies){
        buildProjects(names);
        addDependencies(dependencies);
    }
    public void buildProjects(String[] names){ //프로젝트 정의하는 함수
        projects = new HashMap<String, Project>();
        for(int i=0; i< names.length; i++){
            //이름과 프로젝트 객체 hashmap에 저장
            projects.put(names[i], new Project(names[i]));
        }
    }
    public void addDependencies(String[][] dependencies){
        for(String[] dependency : dependencies){
            //앞에는 먼저 처리되어야하고 뒤엔 나중에처리
            Project before = findProject(dependency[0]);
            Project after = findProject(dependency[1]);
            after.addDependency(before);
        }
    }
    private int index;
    public Project[] buildOrder(){
        initMarkingFlages();
        Project[] ordered = new Project[this.projects.size()];
        index = 0;
        for(Project project : this.projects.values()){
            buildOrder(project, ordered);
        }
        return ordered;
    }
    public void buildOrder(Project project, Project[] ordered){
        if(!project.getDependencies().isEmpty()){
            for(Project p : project.getDependencies()){
                buildOrder(p, ordered);
            }
        }
        if(project.getMarked() == false){
            project.setMarked(true);
            ordered[index] = project;
            index++;
        }
    }
    private void initMarkingFlages(){ //순서 정하기 전 making flag를 false로 치환
        for(Project project : this.projects.values()){
            project.setMarked(false);
        }
    }
    public Project findProject(String name){
        return projects.get(name);
    }
}
public class Tree09 {
    public static void main(String[] args) {
        String[] projects = {"a", "b", "c", "d","e","f","g"};
        String[][] dependencies = {{"f", "a"},{"f", "b"},{"f", "c"},{"b", "a"}
        ,{"c", "a"},{"a", "e"},{"b", "e"},{"d", "g"}};
        ProjectManager pm = new ProjectManager(projects, dependencies);
        Project[] ps = pm.buildOrder();
        for(Project p : ps){
            if(p != null){
                System.out.print(p.getName()+" ");
            }
        }
    }
}
