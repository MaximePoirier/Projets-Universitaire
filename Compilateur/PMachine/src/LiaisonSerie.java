import com.fazecast.jSerialComm.*;

public interface LiaisonSerie {
    int read();
    void write(int val);
    void open();
    void close();

    public static class Local implements LiaisonSerie{
        public int read(){return 1;};
        public void write(int val){};
        public void open(){};
        public void close(){};
    }

    public static class RS232 implements LiaisonSerie {
        SerialPort bob;

        RS232(String nom) {
            SerialPort[] comPorts = SerialPort.getCommPorts();
            int index;
            for(index=0;index<comPorts.length && !comPorts[index].toString().contains(nom);index++);
            if (index<comPorts.length) {
                bob = comPorts[index];
            }
        }

        public int read() {
            byte[] input = new byte[2];
            bob.readBytes(input,2);
            int res = (input[0] & 0xFF) | (input[1] <<8);
            return res;
        }

        public void write(int val) {
            try {
                byte[] data = new byte[]{(byte) (val & 0xFF), (byte)((val>>8) & 0xFF) };
                bob.writeBytes(data,2);
            } catch (Exception e) { e.printStackTrace(); }
        }

        public void open() {
            bob.openPort();
            bob.setComPortTimeouts(SerialPort.TIMEOUT_READ_BLOCKING, 0, 0);
        }

        public void close() {
            bob.closePort();
        }
    }
}
