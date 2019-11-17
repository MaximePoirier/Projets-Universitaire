public class PMachine {
    Instruction pcode[];
    LiaisonSerie liaisonSerie;
    int mem[] = new int[1000];
    int SP = 0;
    int PC = 0;
    int BASE = 0;
    boolean run = false;


    public PMachine(Instruction[] pcode, LiaisonSerie liaisonSerie) {
        this.pcode = pcode;
        this.liaisonSerie = liaisonSerie;
    }

    public PMachine(Instruction[] pcode) {
        this(pcode, null);
    }

    public void exec() {
        if(liaisonSerie != null){
            liaisonSerie.open();
        }
        run = true;
        while(run)
            pcode[PC].exec(this);
        if(liaisonSerie != null){
            liaisonSerie.close();
        }
    }

    public String toString() {
        StringBuffer res = new StringBuffer();
        for (int i=0; i< pcode.length; i++)
            res.append(i + "\t" + pcode[i] +"\n");
        return res.toString();
    }
}
