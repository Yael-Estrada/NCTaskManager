package mx.edu.j2se.estrada.tasks;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.javaws.IconUtil;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAmount;
import java.util.Iterator;

public class TaskIO {

        public static void writeBinary(AbstractTaskList tasks, OutputStream out) throws IOException {
                DataOutputStream dos=(DataOutputStream) out;
                try {
                        dos.writeInt(tasks.size());
                        dos.flush();
                        Iterator<Task> it=tasks.iterator();
                        while(it.hasNext()) {
                                Task t = it.next();
                                dos.writeInt(t.getTitle().length());
                                dos.flush();
                                dos.write(t.getTitle().getBytes(StandardCharsets.UTF_8));
                                dos.flush();
                                dos.writeBoolean(t.isActive());
                                dos.flush();
                                long inter=0;
                                if(t.getRepeatInterval()!=null)
                                        inter=t.getRepeatInterval().inSeconds();
                                dos.writeLong(t.isRepeated() ?inter: 0);
                                dos.flush();
                                if (t.isRepeated()) {
                                        dos.writeLong(t.getStartTime().atZone(ZoneId.systemDefault()).toEpochSecond());
                                        dos.flush();
                                        dos.writeLong(t.getEndTime().atZone(ZoneId.systemDefault()).toEpochSecond());
                                        dos.flush();
                                } else {
                                        dos.writeLong(t.getTime().atZone(ZoneId.systemDefault()).toEpochSecond());
                                        dos.flush();
                                }
                        }
                } catch (IOException e) {
                        throw new IOException("Error al escribir en el buffer de salida");
                }
                dos.close();
        }

        public static void write(AbstractTaskList tasks, File file) throws FileNotFoundException {
                try {
                        DataOutputStream outputStream=new DataOutputStream(new FileOutputStream(file));
                        writeBinary(tasks,outputStream);
                } catch (FileNotFoundException e) {
                        throw new FileNotFoundException("No se encontró el archivo");
                } catch (IOException e) {
                        System.err.println(e.getMessage());
                }
        }

        public static void readBinary(AbstractTaskList tasks, InputStream in) throws IOException {
                DataInputStream dis=(DataInputStream) in;
                try {
                        int size = dis.readInt();
                        for(int i=0;i<size;i++){
                                Task t;
                                int lenName=dis.readInt();
                                byte[] titleBytes=new byte[lenName];
                                dis.read(titleBytes,0,lenName);
                                String title=new String(titleBytes);
                                boolean active=dis.readBoolean();
                                long interval=dis.readLong();
                                if(interval==0){
                                        long time=dis.readLong();
                                        t=new Task(title, LocalDateTime.ofInstant(Instant.ofEpochSecond(time), ZoneId.systemDefault()));
                                        t.setActive(active);
                                        tasks.add(t);
                                }
                                else{
                                        long startTime=dis.readLong();
                                        long endTime=dis.readLong();
                                        t=new Task(title,LocalDateTime.ofInstant(Instant.ofEpochSecond(startTime), ZoneId.systemDefault())
                                                        ,LocalDateTime.ofInstant(Instant.ofEpochSecond(endTime), ZoneId.systemDefault()),
                                                        Timestamp.fromSeconds(interval));

                                        t.setActive(active);
                                        tasks.add(t);
                                }
                        }
                }
                catch(IOException e){
                        throw new IOException("Error al leer el buffer");
                }

        }

        public static void read(AbstractTaskList tasks, File file) throws FileNotFoundException {
                try {
                        DataInputStream dis = new DataInputStream(new FileInputStream(file));
                        readBinary(tasks,dis);
                } catch (FileNotFoundException e) {
                        throw new FileNotFoundException("No se encontró el archivo");
                } catch (IOException e) {
                        System.err.println(e.getMessage());
                }

        }

        public static void write(AbstractTaskList tasks, Writer out) throws IOException {
                OutputStreamWriter osw=(OutputStreamWriter) out;
                Gson gson = new Gson();
                String s=gson.toJson(tasks);
                osw.write(s.length());
                osw.flush();
                osw.write(s,0,s.length());
                osw.flush();
                osw.close();

        }

        public static void read(AbstractTaskList tasks,Reader in) throws IOException {
                InputStreamReader isr=(InputStreamReader) in;
                AbstractTaskList task=null;
                Gson gson=new Gson();
                try {
                        int len=isr.read();
                        char[] buffer=new char[len];
                        isr.read(buffer,0,len);
                        String data=new String(buffer);
                        task=gson.fromJson(data,LinkedTaskList.class);
                        Iterator<Task> it =task.iterator();
                        while(it.hasNext()){
                                tasks.add(it.next());
                        }
                        in.close();
                } catch (IOException e) {
                        throw new IOException("Error al leer el buffer");
                }
        }

        public static void writeText(AbstractTaskList tasks, File file) throws FileNotFoundException {
                try {
                        OutputStreamWriter osw=new OutputStreamWriter(new FileOutputStream(file));
                        write(tasks,osw);
                } catch (FileNotFoundException e) {
                        throw new FileNotFoundException("No se encontró el archivo");
                } catch (IOException e) {
                        System.err.println(e.getMessage());
                }
        }

        public static void readText(AbstractTaskList tasks,File file) throws FileNotFoundException {
                try{
                        InputStreamReader isr=new InputStreamReader(new FileInputStream(file));
                        read(tasks,isr);
                } catch (FileNotFoundException e) {
                        throw new FileNotFoundException("No se encontró el archivo");
                } catch (IOException e) {
                        System.err.println(e.getMessage());
                }
        }
}
