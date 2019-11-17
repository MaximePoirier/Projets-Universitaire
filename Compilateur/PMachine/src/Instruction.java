import java.io.BufferedReader;
import java.util.Scanner;

public interface Instruction {

    public void exec(PMachine pMachine);

    /*
    instruction : 'ADD' | 'SUB' | 'MUL' | 'DIV' |
    'EQL' | 'NEQ'| 'GTR' | 'LSS' | 'GEQ' | 'LEQ' |
    'PRN' | 'INN' |
    'INC' ('-')? INT |
    'LDI' ('-')? INT | 'LDA' INT | 'LDV' |
    'STO' |
    'BRN' INT | 'BZE' INT |
    'HLT'
     */

    public static class ADD implements Instruction {
        public void exec(PMachine pMachine){
            pMachine.mem[pMachine.SP-2] += pMachine.mem[pMachine.SP-1];
            pMachine.SP--;
            pMachine.PC++;
        }

        public String toString(){
            return "ADD";
        }
    }

    public static class SUB implements Instruction {
        public void exec(PMachine pMachine){
            pMachine.mem[pMachine.SP-2] -= pMachine.mem[pMachine.SP-1];
            pMachine.SP--;
            pMachine.PC++;
        }
        public String toString(){
            return "SUB";
        }
    }

    public static class MUL implements Instruction {
        public void exec(PMachine pMachine){
            pMachine.mem[pMachine.SP-2] *= pMachine.mem[pMachine.SP-1];
            pMachine.SP--;
            pMachine.PC++;
        }
        public String toString(){
            return "MUL";
        }
    }

    public static class DIV implements Instruction {
        public void exec(PMachine pMachine){
            pMachine.mem[pMachine.SP-2] /= pMachine.mem[pMachine.SP-1];
            pMachine.SP--;
            pMachine.PC++;
        }
        public String toString(){
            return "DIV";
        }
    }

    public static class EQL implements Instruction {
        public void exec(PMachine pMachine){
            boolean res = pMachine.mem[pMachine.SP-2] == pMachine.mem[pMachine.SP-1];
            pMachine.mem[pMachine.SP-2] = res ?1:0;
            pMachine.SP--;
            pMachine.PC++;
        }
        public String toString(){
            return "EQL";
        }
    }
    public static class NEQ implements Instruction {
        public void exec(PMachine pMachine){
            boolean res = pMachine.mem[pMachine.SP-2] != pMachine.mem[pMachine.SP-1];
            pMachine.mem[pMachine.SP-2] = res ?1:0;
            pMachine.SP--;
            pMachine.PC++;
        }
        public String toString(){
            return "NEQ";
        }
    }
    public static class GTR implements Instruction {
        public void exec(PMachine pMachine){
            boolean res = pMachine.mem[pMachine.SP-2] > pMachine.mem[pMachine.SP-1];
            pMachine.mem[pMachine.SP-2] = res ?1:0;
            pMachine.SP--;
            pMachine.PC++;
        }
        public String toString(){
            return "GTR";
        }
    }
    public static class LSS implements Instruction {
        public void exec(PMachine pMachine){
            boolean res = pMachine.mem[pMachine.SP-2] < pMachine.mem[pMachine.SP-1];
            pMachine.mem[pMachine.SP-2] = res ?1:0;
            pMachine.SP--;
            pMachine.PC++;
        }
        public String toString(){
            return "LSS";
        }
    }
    public static class GEQ implements Instruction {
        public void exec(PMachine pMachine){
            boolean res = pMachine.mem[pMachine.SP-2] >= pMachine.mem[pMachine.SP-1];
            pMachine.mem[pMachine.SP-2] = res ?1:0;
            pMachine.SP--;
            pMachine.PC++;
        }
        public String toString(){
            return "GEQ";
        }
    }
    public static class LEQ implements Instruction {
        public void exec(PMachine pMachine){
            boolean res = pMachine.mem[pMachine.SP-2] <= pMachine.mem[pMachine.SP-1];
            pMachine.mem[pMachine.SP-2] = res ?1:0;
            pMachine.SP--;
            pMachine.PC++;
        }
        public String toString(){
            return "LEQ";
        }
    }

    public static class PRN implements Instruction {
        public void exec(PMachine pMachine){
            System.out.println(pMachine.mem[pMachine.SP-1]);
            pMachine.SP--;
            pMachine.PC++;
        }
        public String toString(){
            return "PRN";
        }
    }

    public static class INN implements Instruction {
        Scanner scanner = new Scanner(System.in);
        public void exec(PMachine pMachine){
            System.out.println("-> ");
            pMachine.mem[pMachine.SP] = scanner.nextInt();
            pMachine.SP++;
            pMachine.PC++;
        }
        public String toString(){
            return "INN";
        }
    }

    public static class INC implements Instruction {
        int param;

        public INC(int param) {
            this.param = param;
        }

        public void exec(PMachine pMachine){
            pMachine.SP += param;
            pMachine.PC++;
        }
        public String toString(){
            return "INC " +param;
        }
    }

    public static class LDI implements Instruction {
        int param;

        public LDI(int param) {
            this.param = param;
        }

        public void exec(PMachine pMachine){
            pMachine.mem[pMachine.SP] = param;
            pMachine.SP++;
            pMachine.PC++;
        }
        public String toString(){
            return "LDI " +param;
        }
    }
    public static class LDA implements Instruction {
        int param;

        public LDA(int param) {
            this.param = param;
        }

        public void exec(PMachine pMachine){
            pMachine.mem[pMachine.SP] = param;
            pMachine.SP++;
            pMachine.PC++;
        }
        public String toString(){
            return "LDA " +param;
        }
    }

    public static class LDV implements Instruction {
        public void exec(PMachine pMachine){
            pMachine.mem[pMachine.SP-1] = pMachine.mem[pMachine.mem[pMachine.SP-1]];
            pMachine.PC++;
        }
        public String toString(){
            return "LDV";
        }
    }

    public static class STO implements Instruction {
        public void exec(PMachine pMachine){
            pMachine.mem[pMachine.mem[pMachine.SP-2]]=pMachine.mem[pMachine.SP-1];
            pMachine.SP -=2;
            pMachine.PC++;
        }
        public String toString(){
            return "STO";
        }
    }

    public static class BRN implements Instruction {
        int param;

        public BRN(int param) {
            this.param = param;
        }

        public void setParam(int param){this.param = param;}

        public void exec(PMachine pMachine){
            pMachine.PC = param;
        }
        public String toString(){
            return "BRN " +param;
        }
    }

    public static class BZE implements Instruction {
        int param;

        public BZE(int param) {
            this.param = param;
        }

        public void setParam(int param){this.param = param;}

        public void exec(PMachine pMachine){
            if (pMachine.mem[pMachine.SP-1] == 0)
                pMachine.PC = param;
            else
                pMachine.PC++;
            pMachine.SP--;
        }

        public String toString(){
            return "BZE " +param;
        }
    }

    public static class HLT implements Instruction {
        public String toString(){
            return "HLT";
        }
        public void exec(PMachine pMachine){
            pMachine.run = false;
        }
    }

    public static class CAL implements Instruction {
        int param;
        public CAL( int param){
            this.param = param;
        }
        public String toString(){
            return "CAL";
        }
        public void exec(PMachine pMachine){
            pMachine.mem[pMachine.SP] = pMachine.PC +1;
            pMachine.mem[pMachine.SP+1] = pMachine.BASE;
            pMachine.SP += 2;
            pMachine.PC = param;
            pMachine.BASE = pMachine.SP;
        }
    }

    public static class RET implements Instruction {
        int param;
        public RET( int param){
            this.param = param;
        }
        public String toString(){
            return "RET";
        }
        public void exec(PMachine pMachine){
            pMachine.SP = pMachine.BASE-2-param;
            pMachine.PC = pMachine.mem[pMachine.BASE - 2];
            pMachine.BASE = pMachine.mem[pMachine.BASE - 1];
        }
    }

    public static class LDL implements Instruction {
        int param;
        public LDL( int param){
            this.param = param;
        }
        public String toString(){
            return "LDL";
        }
        public void exec(PMachine pMachine){
            pMachine.mem[pMachine.SP] = param + pMachine.BASE;
            pMachine.SP++;
            pMachine.PC++;
        }
    }

    public static class ROB implements Instruction {
        int param;
        int res;
        public ROB(int param, int res) {
            this.param = param;
            this.res=res;
        }

        public void exec(PMachine pMachine){

            if(pMachine.liaisonSerie != null) { //gerer le cas ou la liaison n'est pas activee
                for (int i = 0; i < param; i++)
                    pMachine.liaisonSerie.write(pMachine.mem[pMachine.SP - param + i]);
                pMachine.SP -= param;
                for (int i = 0; i < res; i++)
                    pMachine.mem[pMachine.SP + i] = pMachine.liaisonSerie.read();
                pMachine.SP+= res;
            }else{
                String msg = " ( ";
                for (int i = 0; i < param - 1; i++)
                    msg = msg + pMachine.mem[pMachine.SP - param + i]+ ", ";
                pMachine.SP -= param;
                msg = msg.substring(0, msg.length() - 2) + " ) ";
                System.out.println("pas de liaison : ROB "+param+" "+res+msg+"non executé sur le robot.");

            }

            pMachine.PC++;
        }

        public String toString(){
            return "ROB " +param + " " + res;
        }
    }

    public static class SLE implements Instruction {

        public void exec(PMachine pMachine){
            try {
                Thread.sleep(pMachine.mem[pMachine.SP - 1]);
            } catch (Exception e){e.printStackTrace();};
            pMachine.SP--;
            pMachine.PC++;
        }

        public String toString(){
            return "SLE";
        }
    }
}
