import java.util.ArrayList;

class Controller {
    private ArrayList<Function> functions;
    
    public Controller(){
        functions = new ArrayList<Function>();
    }

    public Function addFunction(String name){
        Function f = new Function(name);
        functions.add(f);
        return f;
    }

    public void printOn(){
        for(Function f: functions){
            if(f.getStatus())
                System.out.println(f.getName());
        }
    }

    class Function{
        private String name;
        private boolean status;
        private ArrayList<Function> incompatibles;

        public Function(String name){
            this.name = name;
            status = false;
            incompatibles = new ArrayList<Function>();
        }

        public void turnOn(){
            status = true;
            for(Function f: incompatibles){
                f.turnOff();
            }
        }

        public void turnOff(){
            status = false;
        }

        public void setIncompatible(Function f){
            if(!incompatibles.contains(f))
                incompatibles.add(f);
            if(!f.isFunctionAlreadyIncompatible(this))
                f.setIncompatible(this);
        }
        
        public boolean isFunctionAlreadyIncompatible(Function f){
            return incompatibles.contains(f);
        }

        public boolean getStatus(){
            return status;
        }

        public String getName(){
            return name;
        }
    }
}