import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;

public class Main {

    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("C:/Intellij Idea/txt.txt"));
            String line = reader.readLine();// считывает строку и возвращает её

            Stack digit = new Stack();
            Stack letter = new Stack();
            Stack other = new Stack();

            while (line != null) {
                for (char c : line.toCharArray()) { //смотрим каждый символ из символьного массива
                    if (Character.isDigit(c)) { //проверяем явл. ли он цифрой
                        digit.push(c);// если да, то цифра отправляется в стек
                    } else if (Character.isLetter(c)) { // тоже самое с буквами и с остальными символами
                        letter.push(c);
                    } else {
                        other.push(c);
                    }
                }
                line = reader.readLine(); // еще раз смотрим строку
            }

            System.out.println("Цифры:");
            digit.printSt(); // Вывод стека
            System.out.println("\nБуквы:");
            letter.printSt();
            System.out.println("\nОстальные символы:");
            other.printSt();

            reader.close(); //закрытие потока ввода ресурсов
        } catch (IOException e) { // закрытия try и вывод ошибки ввода вывода
            e.printStackTrace();
        }
    }

    static class Node {
        char data; //данные в узле
        Node next;
        public Node(char data) { // создание конструктора, а также инициализация узла
            this.data = data;
        }
    }

    static class Stack {
        Node upper; // верхний элемент списка, последний добавленный
        public boolean isEmpty() {
            return upper == null;  // проверка на пустоту стека
        }

        public void push(char data) {// добавление нового узла с символом дата в стек
            Node newNode = new Node(data); //создание нового узла
            newNode.next = upper; // создание ссылки узла на верхний элемент списка
            upper = newNode;// новый узел становится верхним элементом списка
        }

        public char top() { // возвращает значение символа из стека но не удаляет его
            if (isEmpty()) {  // проверка на пустоту стека
                throw new IllegalStateException("Стек пустой "); //Ошибка
            }
            char data = upper.data; // в дату записыв. значение из верхнего узла стека
            upper = upper.next; // обновление ссылки
            return data;
        }

        public void printSt() {
            Stack reverseStack = new Stack(); // Создаем новый стек для обратного вывода
            Node current = upper; // Устанавливаем текущий узел на верхний элемент стека
            while (current != null) {
                reverseStack.push(current.data); // Помещаем символы в новый стек на вершину стека
                current = current.next; // Переходим к следующему узлу в стеке
            }
            while (!reverseStack.isEmpty()) { //цикл пока стек не пустой
                System.out.print(reverseStack.top()+ " "); // Выводим символы в обратном порядке
            }
        }
    }
}
