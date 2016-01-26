package com.nebiroz.thinkingjava.lessons.chapter05;

/**
 * Created by Frost on 07.11.2015.
 */

import com.nebiroz.thinkingjava.lessons.BaseExample;

class ExampleThread implements Runnable{
    private int counter = 0;

    public void run(){
        try{
            Thread.sleep(10000);
            counter++;
        }
        catch(InterruptedException ex){
            System.out.println("Словил Exception.");
        }
    }
}

public class Exercise12 extends BaseExample{
    static int result = 0;

    /**
     * Описательная части примера.
     * Здесь нужно заполнить поля для вывода описания примера.
     * header - заголовок программы. Здесь указывается просто номер и тип задачи
     * description - описание задачи, которую нужно выполнить
     * codeExample - полученный в хоже решения исходный код
     */
    public Exercise12(){
        header = "Exercise 12";
        description = "Включите в класс с именем Tank (емкость), который можно наполнить и опустошить.<br/>"
                + "Условие \"готовности\" требует, чтобы он был пуст перед очисткой. Напишите метод finalize(), проверяющий это условие.<br/>"
                + "В методе main() протестируйте возможные случаи использования вашего класса.<br/>";
        codeExample = " class TestClass{\n"
                + "     public void finalize(){\n"
                + "         System.out.println(\"Сработал метод finalize()\");\n"
                + "     }\n"
                + " }\n"
                + "...\n"
                + "TestClass tst = new TestClass();\n"
                + "tst = null;\n"
                + "System.gc();\n"
                + "printOutLn(\"Надпись, что сработал метод, не появилась. Значит ресурс не очищается под действием JVM.\");\n"
                + "\n"
                + "Вывод строк по ходу программы:\n"
                + " INFO  [stdout] (Finalizer) Сработал метод finalize()\n"
                + " Надпись, что сработал метод, не появилась. Значит ресурс не очищается под действием JVM.\n"
                + " Это означает, что при ручной очистке ресурсов происходит удаление не активных ресурсов когда нужно нам.\n";
        printExampleText();
    }

    /**
     * Исходный код для примера.
     * Нужно написать примерочный код класса.
     */
    class Tank{
        private boolean isReady = false;
        public Tank() { }
        public Tank(boolean ready){
            isReady = ready;
        }

        public void rightClean(){
            isReady = false;
        }
        public void finalize(){
            if (isReady){
                System.out.println("Ошибка! Готовность должна быть false" + this.toString());
            }
            else{
                System.out.println("Удаление прошло успешно!" + this.toString());
            }
        }
    }

    /**
     * Исходный код вызывающего блока.
     * Здесь нужно написать всю цепочку вызовов.
     * @return String результат выполнинения исходного кода для примера
     */
    @Override
    public String runExample() {
        startOutResult();

        /**
         * Здесь нужно написать цепочки вызовов функций
         */
        System.out.println("Создаем правильный танк");
        Tank tnk = new Tank(true);
        tnk.rightClean();

        System.out.println("Теряем ссылку");
        new Tank(true);

        System.out.println("Очищаем ресурсы");
        System.gc();

        System.out.println("Смотрим результат");

        stopOutResult();
        return endExeceuteExample();
    }
}