/*
Прайсы 2
CrUD для таблицы внутри файла
Считать с консоли имя файла для операций CrUD

Программа запускается с одним из следующих наборов параметров:
-u id productName price quantity
-d id

Значения параметров:
где id - 8 символов
productName - название товара, 30 символов
price - цена, 8 символов
quantity - количество, 4 символа
-u - обновляет данные товара с заданным id
-d - производит физическое удаление товара с заданным id (все данные, которые относятся к переданному id)

В файле данные хранятся в следующей последовательности (без разделяющих пробелов):
id productName price quantity
Данные дополнены пробелами до их длины

Пример:
19847   Шорты пляжные синие           159.00  12
198479  Шорты пляжные черные с рисунко173.00  17
19847983Куртка для сноубордистов, разм10173.991234


Требования:
1. Программа должна считать имя файла для операций CrUD с консоли.
2. При запуске программы без параметров список товаров должен остаться неизменным.
3. При запуске программы с параметрами "-u id productName price quantity" должна обновится информация о товаре в файле.
4. При запуске программы с параметрами "-d id" строка товара с заданным id должна быть удалена из файла.
5. Созданные для файлов потоки должны быть закрыты.
 */


import java.io.*;
import java.util.ArrayList;

    public class Solution {

        ArrayList<String> listId = new ArrayList<>();
        ArrayList <String> listText = new ArrayList<>();
        int id=0;
        String stringId="";


        public static void main(String[] args) throws IOException {
            if (args.length == 0) {return;}
            if (!args[0].equals("-u") && !args[0].equals("-d") ) {return;}

            Solution solution = new Solution();

            BufferedReader readerFileName = new BufferedReader(new InputStreamReader(System.in));
            String fileName = readerFileName.readLine();
            readerFileName.close();


            solution.reader(fileName);
            solution.createNomberPosition(solution.createId(args[1]));
            String names = "";
            if (args.length > 2){
                names = solution.createNames(args[2], args[3], args[4]);
            }
            solution.processArgument(args[0], names);
            solution.writer(fileName);

        }

        private static String formatting(int count, String name){
            int format = count - name.length();
            if (format < 0){
                name = name.substring(0, count);
            }
            if (format >0){
                for (int i = format; i>0; i--) {
                    name += " ";
                }
            }
            return name;
        }

        private String createNames (String name, String price, String quantity){

            String result = formatting(30, name) + formatting(8, price) +formatting(4, quantity);
            return result;
            }

        private void reader(String fileName) throws IOException {

            FileReader fileReader = new FileReader(fileName);
            BufferedReader readerFile = new BufferedReader(fileReader);

            String string="";
            while (readerFile.ready()){
                string=readerFile.readLine();
                listId.add(string.substring(0, 8));
                listText.add(string.substring(8));
            }

            fileReader.close();
            readerFile.close();

        }

        private String createId (String idSrting){
            stringId = formatting(8, idSrting);

            return stringId;
        }

        private int createNomberPosition (String stringId){
            for (int i=0; i < listId.size(); i++){
                if (stringId.equals(listId.get(i))) {
                    id = i;
                }
            }

            return id;
        }

        private void writer (String fileName) throws IOException {
            FileWriter fr = new FileWriter(fileName);
            BufferedWriter br = new BufferedWriter(fr);

            for (int i=0; i<listId.size(); i++){
                br.write(listId.get(i)+listText.get(i) );
                br.newLine();
                br.flush();
            }

            fr.close();
            br.close();

        }

        private void processArgument(String arg, String string){
            if (arg.equals("-u")){
                listId.set(id, stringId);
                listText.set(id, string);

            }
            if (arg.equals("-d")){
                listId.remove(id);
                listText.remove(id);
            }
        }

    }


