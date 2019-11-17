import java.util.ArrayList;
import java.util.HashMap;

public class SymboleTable {
    public HashMap<String,Donnee> table;
    public HashMap<String,Donnee> tableLocal;
    public boolean estGlobal;
    public int sizeLocal;
    public int size;


    public SymboleTable(){
        this.table = new HashMap<String, Donnee>();
        this.size = 0;
        this.estGlobal = true;
    }

    public void setLocal(){
        this.estGlobal = false;
        this.tableLocal = new HashMap<String, Donnee>();
        this.sizeLocal = 0;
    }

    public  void setGlobal(){
        this.estGlobal = true;
    }

    public void putVar(String id, Type type){
        if(estGlobal){
            Donnee donnee = new Donnee(this.size,type,true);
            this.table.put(id,donnee);
            this.size+= type.size();
        }
        else{
            this.tableLocal.put(id, new Donnee(this.sizeLocal,type,false));
            this.sizeLocal += type.size();
        }
    }

    public void putListVar(ArrayList<String> variables, Type type){
        for(int i = 0; i < variables.size(); i++){
            putVar(variables.get(i),type);
        }
    }

    public int getSize(){
        return estGlobal? size : sizeLocal;
    }

    public int getAdresse(String id){
        Donnee donnee = this.table.get(id);
        return donnee.getAd();
    }

    public Donnee getDonnee(String id){
        if(!estGlobal){
            Donnee res = tableLocal.get(id);
            if(res != null){
                return res;
            }
        }
        return this.table.get(id);
    }
}
