import java.util.ArrayList;

class homework {
    public static void main(String[] argv){
        Controller c = new Controller();
        Controller.Function ac = c.addFunction("Aria_condizionata");
        Controller.Function risc = c.addFunction("Riscaldamento");
        Controller.Function sedile = c.addFunction("Sedile_riscaldato");
        ac.setIncompatible(risc);
        ac.setIncompatible(sedile);
        ac.turnOn();
        c.printOn();
        System.out.println("-----");
        risc .turnOn();
        sedile .turnOn();
        c.printOn();
    }
}

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
        for(Function s: functions){
            if(s.getStatus())
                System.out.println(s.getName());
        }
    }


    class Function{

        private String name;
        private boolean status;
        private ArrayList<Function> incompatible;

        public Function(String name){
            this.name = name;
            status = false;
            incompatible = new ArrayList<Function>();
        }

        public void turnOn(){

            status = true;
        }

        public void turnOff(){
            status = false;
        }

        public void setIncompatible(Function f){
            incompatible.add(f);

        }

        public boolean getStatus(){
            return status;
        }

        public String getName(){
            return name;
        }

    }
}


