import java.util.ArrayList;

public interface Type {
    public int size();


    public static class Int implements Type {
        public int size(){
            return 1;
        }
    }

    public static class Bool implements Type {
        public int size(){
            return 1;
        }
    }

    public static class Tab implements Type {
        public int taille;
        public Type type;

        public Tab(int taille, Type type){
            this.taille=taille;
            this.type = type;
        }

        public Type getType(){
            return this.type;
        }

        @Override
        public int size() {
            return taille*type.size();
        }
    }

    public static class Record implements Type {
        ArrayList<String> champs;
        ArrayList<Type> types;

        public Record(ArrayList<String> champs, ArrayList<Type> types){
            this.champs = champs;
            this.types = types;
        }

        @Override
        public int size() {
            int res = 0;
            for(Type t:types)
                res += t.size();
            return res;
        }

        public int adresse(String id){
            int res = 0;
            int i=0;
            while(i <champs.size() && !champs.get(i).equals(id)){
                res += types.get(i).size();
                i++;
            }
            return res;
        }

        public Type type(String id){
            int i =0;
            while(i <champs.size() && !champs.get(i).equals(id)){
                i++;
            }
            return types.get(i);
        }
    }

    public static class Fonction implements Type{

        Type res;
        ArrayList<Type> typesParam;
        ArrayList<String> nomsParam;
        int sizeParam;

        public Fonction(Type res, ArrayList<Type> typesParam, ArrayList<String>  nomsParam){
            this.res = res;
            this.typesParam = typesParam;
            this.sizeParam = typesParam.size();
            this.nomsParam = nomsParam;
        }

        @Override
        public int size() {
            return 0;
        }

        @Override
        public String toString() {
            String message = " Type : Fonction (";
            for(int i=0;i<typesParam.size();i++){
                message.concat(" " + nomsParam.get(i) + " : "+typesParam.get(i) + ",");
            }
            return message.substring(0,message.length() - 1) + " ) ";
        }
    }
}
